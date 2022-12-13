package com.example.foodinfo.utils


sealed class UiState {
    class Loading : UiState()
    class Success : UiState()
    class Error(val message: String, val error: Exception) : UiState()

    fun equalState(other: Any): Boolean {
        if (other is Error) {
            this as Error
            return this.message == other.message &&
                    this.error.javaClass == other.error.javaClass
        }
        return this.javaClass == other.javaClass
    }
}