package com.example.mundocai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mundocai.core.hide
import com.example.mundocai.core.show
import com.example.mundocai.databinding.ActivityMainBinding
import com.example.mundocai.ui.quiz.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.pager)

        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Quiz"
                1 -> tab.text = "Ranking"
            }
        }.attach()


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
                    binding.pager.hide()
                    binding.tabLayout.hide()
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.registerFragment -> {
                    binding.pager.hide()
                    binding.tabLayout.hide()
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.avatarProfileFragment -> {
                    binding.pager.hide()
                    binding.tabLayout.hide()
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()

                }
                R.id.forgotPasswordFragment -> {
                    binding.pager.hide()
                    binding.tabLayout.hide()
                    binding.bottomNavigationView.hide()
                    binding.include.toolbar.hide()
                }

                R.id.quizFragment -> {
                    binding.navHostFragment.hide()
                    binding.tabLayout.show()
                    binding.pager.show()

                }


                else -> {
                    binding.navHostFragment.show()
                    binding.pager.hide()
                    binding.tabLayout.hide()
                    binding.bottomNavigationView.show()
                    binding.include.toolbar.show()
                    val imageProfile = FirebaseAuth.getInstance().currentUser?.photoUrl
                    Glide.with(this).load(imageProfile).centerCrop()
                        .into(binding.include.profileToolbar)

                }
            }
        }
    }


}

