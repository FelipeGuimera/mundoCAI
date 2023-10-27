package com.example.mundocai

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.mundocai.core.hide
import com.example.mundocai.core.show
import com.example.mundocai.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

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


    }

    private fun observeDestinationChange() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {

                R.id.splashFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.loginFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.registerFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.avatarProfileFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.forgotPasswordFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()


                }

                else -> {
                    binding.bottomNavigationView.show()
                    binding.include.toolbar.show()
                    val imageProfile = FirebaseAuth.getInstance().currentUser?.photoUrl
                    Glide.with(this).load(imageProfile).centerCrop().into(binding.include.profileToolbar)

                }
            }
        }
    }


}

