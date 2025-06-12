package com.example.trainingapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trainingapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController//findNavController(R.id.nav_host_fragment_activity_main)
        val configuration = AppBarConfiguration(
            setOf(
                R.id.navigation_activity, R.id.navigation_personal
            )
        )
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }
    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_toolbar_menu, menu)
        menu?.iterator()?.forEach {
            val icon = it.icon
            icon?.setColorFilter(getColor(R.color.purple_500),PorterDuff.Mode.SRC_IN)
            it.setIcon(icon)
        }
        return super.onCreateOptionsMenu(menu)
    }
    */
}