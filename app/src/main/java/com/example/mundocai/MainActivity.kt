package com.example.mundocai

import android.app.Notification.Action
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mundocai.core.hide
import com.example.mundocai.core.show
import com.example.mundocai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        observeDestinationChange()

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar)

    }

    private fun observeDestinationChange() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.bottomNavigationView.hide()
                    supportActionBar?.hide()
                }
                R.id.registerFragment -> {
                    binding.bottomNavigationView.hide()
                    supportActionBar?.hide()
                }
                R.id.splashFragment -> {
                    binding.bottomNavigationView.hide()
                    supportActionBar?.hide()
                }
                R.id.avatarProfileFragment -> {
                    binding.bottomNavigationView.hide()
                    supportActionBar?.hide()
                }
                R.id.forgotPasswordFragment -> {
                    binding.bottomNavigationView.hide()
                    supportActionBar?.hide()

                }

                else -> {
                    binding.bottomNavigationView.show()

                }
            }
        }
    }


}

