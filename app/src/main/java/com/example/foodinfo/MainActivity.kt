package com.example.foodinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodinfo.databinding.ActivityRecipesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_recipes) as NavHostFragment
        val navController = navigationHost.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(
            bottomNavigationView, navController
        )

        // не долго думая сделал так как ты и рассказывал
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.f_home,
                R.id.f_explore,
                R.id.f_settings -> bottomNavigationView.isVisible = true
                else            -> bottomNavigationView.isVisible = false
            }
        }
    }
}