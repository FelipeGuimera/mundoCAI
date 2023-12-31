package com.example.mundocai.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentRegisterBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import com.google.firebase.firestore.FirebaseFirestore


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModel.AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.backTextRegister.setOnClickListener {
            // Aquí navega al home fragment usando NavController
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        singUp()
    }

    private fun singUp() {

        binding.btnSignup.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()

            if (validateUserData(
                    password,
                    confirmPassword,
                    username,
                    email
                )
            ) return@setOnClickListener

            checkUsernameAvailability(username, email, password)

        }
    }

    private fun createUser(email: String, password: String, username: String, profilePicture:String, points:Int) {
        viewModel.signUp(email, password, username, profilePicture, points).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignup.isEnabled = false
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_avatarProfileFragment)
                }
                is Resource.Failure -> {
                    binding.btnSignup.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(), "Error:${
                            result.exception
                        }", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun validateUserData(
        password: String,
        confirmPassword: String,
        username: String,
        email: String
    ): Boolean {
        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Las contraseñas no coinciden"
            binding.editTextPassword.error = "Las contraseñas no coinciden"
            return true
        }

        if (username.isEmpty()) {
            binding.editTextUsername.error = "Campo vacío"
            return true
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Campo vacío"
            return true
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Campo vacío"
            return true
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextConfirmPassword.error = "Campo vacío"
            return true
        }
        return false
    }

    private fun checkUsernameAvailability(username: String, email: String, password: String) {
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")

        usersCollection.whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // El nombre de usuario ya está en uso
                    Toast.makeText(requireContext(), "El nombre de usuario ya está en uso", Toast.LENGTH_SHORT).show()
                } else {
                    // El nombre de usuario está disponible, procede a crear el usuario
                    createUser(email, password, username, profilePicture = "", points = 0)
                }
            }
            .addOnFailureListener { e ->
                // Manejo de error en la consulta
                Toast.makeText(requireContext(), "Error al verificar el nombre de usuario: $e", Toast.LENGTH_SHORT).show()
            }
    }

}