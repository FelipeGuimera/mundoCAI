package com.example.mundocai.ui.quiz

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mundocai.R
import com.example.mundocai.databinding.FragmentResultsBinding
import com.example.mundocai.ui.details.HistoryDetailsFragmentArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ResultsFragment : Fragment(R.layout.fragment_results) {

    private lateinit var binding: FragmentResultsBinding
    private val args by navArgs<ResultsFragmentArgs>()


    private val firestoreDB = FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultsBinding.bind(view)

        binding.correctAnswer.text = args.correctAnswers.toString()
        binding.points.text = args.points.toString()

        updateUserPoints()



        binding.goHome.setOnClickListener {
            // Navegar de resultados a homeFragment y eliminar de la pila de navegación
            findNavController().navigate(R.id.action_resultsFragment_to_homeFragment)
            findNavController().popBackStack(R.id.quizFragment, false)
        }

        binding.rankingText.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_rankingFragment)
        }
    }


    private fun updateUserPoints() {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserUid != null) {
            firestoreDB.collection("users")
                .document(currentUserUid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userPoints = documentSnapshot.getLong("points") ?: 0
                        // Sumar los puntos del usuario con los puntos actuales
                        val gamePoints = args.points
                        val totalPoints = gamePoints + userPoints

                        firestoreDB.collection("users")
                            .document(currentUserUid)
                            .update("points", totalPoints)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("ResultsFragment", "Error getting user points", exception)
                }
        }
    }
}

