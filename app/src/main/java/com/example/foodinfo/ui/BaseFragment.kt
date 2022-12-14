package com.example.foodinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.example.foodinfo.utils.UiState
import com.example.foodinfo.utils.repeatOn
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

/**
 * Base class to avoid boilerplate binding initialization and releasing.
 */
abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    /**
     * Calls in onViewCreated().
     *
     * Function to initialize Views and add clickListeners to them.
     */
    open fun initUI() {}

    /**
     * Calls in onViewCreated() after [initUI]
     *
     * Function to launch all necessary coroutines.
     */
    open fun subscribeUI() {}


    private var _uiState = MutableSharedFlow<UiState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    ).also {
        it.tryEmit(UiState.Loading())
    }
    private val uiState: SharedFlow<UiState> = _uiState.asSharedFlow()

    private val uiChunksState: HashMap<Any, UiState> = hashMapOf()


    /**
     * Observes UI state changes.
     *
     * All repetitions of the same state are filtered out.
     *
     * See [updateUiState] for a description of UI state calculation
     *
     * @param callBack runnable that is executed each time UI state changes
     */
    fun observeUiState(callBack: suspend (UiState) -> Unit) {
        repeatOn(Lifecycle.State.STARTED) {
            uiState.distinctUntilChanged { old, new ->
                old.equalState(new)
            }.collectLatest {
                callBack(it)
            }
        }
    }

    /**
     * Adds new UI chunk and sets it's state to [UiState.Loading].
     * Chunk state will be used to calculate general UI state in [updateUiState]
     *
     * It's recommended to use enums for chunkId type and register all chunks inside [initUI]
     */
    fun registerUiChunk(chunkId: Any) {
        uiChunksState[chunkId] = UiState.Loading()
    }

    /**
     * Updates state for specific UI chunk and recalculate general UI state like this:
     *
     * [UiState.Error] - If any of chunks has state Error (error type and message will be
     * from the first founded chunk with this state).
     *
     * [UiState.Success] - If all chunks has Success state.
     *
     * [UiState.Loading] - Any other conditions.
     */
    fun updateUiState(key: Any, value: UiState): Boolean {
        if (!uiChunksState.containsKey(key)) return false
        uiChunksState[key] = value
        uiChunksState.values.firstOrNull { it is UiState.Error }.also {
            it?.let { uiState ->
                uiState as UiState.Error
                return _uiState.tryEmit(UiState.Error(uiState.message, uiState.error))
            }
        }
        return if (uiChunksState.map { it.value is UiState.Success }.all { it }) {
            _uiState.tryEmit(UiState.Success())
        } else {
            _uiState.tryEmit(UiState.Loading())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        subscribeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}