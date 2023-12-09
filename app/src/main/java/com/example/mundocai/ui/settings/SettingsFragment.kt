package com.example.mundocai.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentProfileBinding
import com.example.mundocai.databinding.FragmentSettingsBinding
import com.example.mundocai.ui.quiz.ExitQuestionDialog


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)


        binding.settingsInformation.setOnClickListener {

        }

        binding.switchNotifications.setOnClickListener {

        }

        binding.signOut.setOnClickListener {
            val signOutDialog = SignOutDialog()
            signOutDialog.show(childFragmentManager, "SignOutDialog")
        }

    }

}