package com.example.mundocai

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
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

                R.id.questionsFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()


                }

                R.id.rankingFragment -> {
                    binding.include.toolbar.show()
                    binding.include.closeCross.hide()
                    binding.bottomNavigationView.hide()
                    binding.include.profileToolbar.hide()
                    binding.include.burgermenu.hide()
                    binding.include.shieldToolbar.hide()
                    binding.include.arrowBack.show()
                    binding.include.titleToolbar.text = "RANKING"
                    binding.include.titleToolbar.show()

                    binding.include.arrowBack.setOnClickListener {
                        navController.navigate(R.id.action_rankingFragment_to_quizFragment)
                    }
                }

                R.id.newsDetailsFragment ->{
                    binding.include.closeCross.hide()
                    binding.include.toolbar.hide()
                    window.statusBarColor = Color.TRANSPARENT
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                }

                R.id.historyDetailsFragment ->{
                    binding.include.closeCross.hide()
                    binding.include.toolbar.hide()
                    window.statusBarColor = Color.TRANSPARENT
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }

                R.id.profileFragment -> {
                    binding.include.toolbar.show()
                    binding.include.closeCross.hide()
                    binding.include.settingsToolbar.show()
                    binding.include.profileToolbar.hide()
                }

                R.id.resultsFragment -> {
                    binding.bottomNavigationView.hide()
                    binding.include.profileToolbar.hide()
                    binding.include.burgermenu.hide()
                    binding.include.shieldToolbar.hide()
                    binding.include.closeCross.hide()
                    binding.include.arrowBack.show()
                    binding.include.titleToolbar.text = "RESULTADOS"
                    binding.include.titleToolbar.show()

                    binding.include.arrowBack.setOnClickListener {
                        navController.navigate(R.id.action_resultsFragment_to_quizFragment)
                    }
                }

                R.id.imagesCarouselFragment->{
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()
                    window.statusBarColor = Color.BLACK
                }




                else -> {
                    binding.bottomNavigationView.show()
                    binding.include.toolbar.show()
                    binding.include.profileToolbar.show()
                    binding.include.burgermenu.show()
                    binding.include.shieldToolbar.show()
                    binding.include.arrowBack.hide()
                    binding.include.titleToolbar.hide()
                    binding.include.closeCross.hide()
                    binding.include.settingsToolbar.hide()
                    window.statusBarColor = Color.parseColor("#EC1C24")
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    val imageProfile = FirebaseAuth.getInstance().currentUser?.photoUrl
                    Glide.with(this).load(imageProfile).centerCrop()
                        .into(binding.include.profileToolbar)


                }
            }
        }
    }


}

