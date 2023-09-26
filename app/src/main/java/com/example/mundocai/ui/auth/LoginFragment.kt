package com.example.mundocai.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.remote.auth.LoginDataSource
import com.example.mundocai.databinding.FragmentLoginBinding
import com.example.mundocai.domain.auth.LoginRepoImpl
import com.example.mundocai.presentation.auth.LoginScreenViewModel
import com.example.mundocai.presentation.auth.LoginScreenViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {


    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<LoginScreenViewModel> { LoginScreenViewModelFactory(
        LoginRepoImpl(
        LoginDataSource()
    )
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
    }


    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email,password)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()) {
            binding.editTextEmail.error = "Por favor, complete este campo"
            return
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Por favor, complete este campo"
            return
        }
    }

    private fun signIn(email: String, password: String){
        viewModel.signIn(email,password).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Welcome ${result.data?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                is Resource.Failure -> {
                    binding.btnSignin.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}