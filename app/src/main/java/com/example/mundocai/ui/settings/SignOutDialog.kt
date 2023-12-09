package com.example.mundocai.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.databinding.DialogQuitBinding
import com.example.mundocai.databinding.DialogSignOutBinding
import com.example.mundocai.ui.auth.LoginFragment
import com.google.firebase.auth.FirebaseAuth

class SignOutDialog() : DialogFragment() {

    private lateinit var binding: DialogSignOutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSignOutBinding.inflate(LayoutInflater.from(context))


        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)



        binding.buttonYes.setOnClickListener {
            cerrarSesion()
            dismiss() // Cerrar el diálogo después de realizar la acción
        }

        binding.buttonNo.setOnClickListener {
            dismiss()
        }

        binding.cross.setOnClickListener {
            dismiss()
        }




        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun cerrarSesion() {

        FirebaseAuth.getInstance().signOut()

    }
}