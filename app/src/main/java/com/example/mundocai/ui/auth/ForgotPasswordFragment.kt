package com.example.mundocai.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentAvatarProfileBinding
import com.example.mundocai.databinding.FragmentForgotPasswordBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)

        binding.backText.setOnClickListener {
            // Aquí navega al home fragment usando NavController
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }

        configureResetPassword()
    }

    private fun resetPassword(email: String) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(context, "Se ha enviado un correo electrónico para restablecer la contraseña. Revise su bandeja de entrada y siga las instrucciones.", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
                } else {
                    // Error: Error al enviar el correo electrónico de restablecimiento de contraseña.
                    val exception = task.exception
                    val errorMessage = exception?.message ?: "Ocurrió un error al enviar el correo electrónico de restablecimiento de contraseña."
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun configureResetPassword(){
        binding.btnResetPassword.setOnClickListener {
            val email = binding.editTextEmail.text.toString() // Obtener el valor del campo de correo electrónico
            resetPassword(email) // Pasar el valor al método resetPassword

        }
    }

}