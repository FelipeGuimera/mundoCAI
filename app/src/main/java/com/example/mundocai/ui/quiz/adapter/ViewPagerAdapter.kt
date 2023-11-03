package com.example.mundocai.ui.quiz.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.manager.Lifecycle
import com.example.mundocai.ui.quiz.QuizFragment
import com.example.mundocai.ui.quiz.RankingFragment

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuizFragment() // Fragmento para la primera posición
            1 -> RankingFragment() // Fragmento para la segunda posición
            else -> QuizFragment()
        }
    }
}


