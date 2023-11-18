package com.example.mundocai.ui.quiz

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
import com.example.mundocai.databinding.FragmentRankingBinding

class ExitQuestionDialog() : DialogFragment() {

    private lateinit var binding: DialogQuitBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogQuitBinding.inflate(LayoutInflater.from(context))


        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)



        binding.buttonYes.setOnClickListener {
            findNavController().navigate(R.id.action_questionsFragment_to_quizFragment)
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
}