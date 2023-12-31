package com.example.mundocai.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.TooltipCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.core.hide
import com.example.mundocai.data.model.User
import com.example.mundocai.data.remote.auth.AuthDataSource
import com.example.mundocai.databinding.FragmentProfileBinding
import com.example.mundocai.databinding.FragmentQuizBinding
import com.example.mundocai.domain.auth.AuthRepoImpl
import com.example.mundocai.presentation.auth.AuthViewModel
import com.example.mundocai.ui.quiz.ResultsFragmentArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private var ganadorUnlocked = false
    private var invictoUnlocked = false
    private var maestroUnlocked = false
    private var misticaUnlocked = false
    private var coperoUnlocked = false
    private var estrellaUnlocked = false
    private var historiadorUnlocked = false
    private var reyUnlocked = false
    private var capitanUnlocked = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)


        getPoints()
        bindingProfile()
        changeProfileImage()
        showAchievementsText()
    }

    private fun bindingProfile() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val username =
                FirebaseAuth.getInstance().currentUser?.displayName // Obtener el nombre del usuario
            val email = FirebaseAuth.getInstance().currentUser?.email

            binding.username.text = username ?: "Nombre de usuario no disponible"
            binding.email.text = email ?: "Email no disponible"

        } else {
            // Manejar el caso en el que el usuario no haya iniciado sesión
            binding.username.text = "Invitado"
        }

        val imageProfile = FirebaseAuth.getInstance().currentUser?.photoUrl

        if (imageProfile != null) {
            Glide.with(this).load(imageProfile).centerCrop().into(binding.avatarProfile)
        } else {
            Glide.with(this).load(R.drawable.rounded_button).centerCrop()
                .into(binding.avatarProfile)
            binding.changeProfileImage.hide()
        }
    }

    private fun getPoints(){
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserUid != null) {
            val firestoreDB = FirebaseFirestore.getInstance()
            firestoreDB.collection("users")
                .document(currentUserUid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userPoints = documentSnapshot.getLong("points") ?: 0
                        updateAchievements(userPoints) // Función para actualizar los logros según los puntos
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("ProfileFragment", "Error getting user points", exception)
                }
        }
    }

    private fun updateAchievements(userPoints: Long) {
        // Verificar y actualizar los logros según la cantidad de puntos del usuario

        if (!ganadorUnlocked && userPoints >= 100) {
            binding.copaGanador.setImageResource(R.drawable.taza)
            binding.progresoGanador.progress = 100
            ganadorUnlocked = true

        }

        if (!invictoUnlocked && userPoints >= 200) {
            binding.copaInvicto.setImageResource(R.drawable.taza)
            binding.progresoInvicto.progress = 100
            invictoUnlocked = true


        }

        if (!maestroUnlocked && userPoints >= 300) {
            binding.copaMaestro.setImageResource(R.drawable.taza)
            binding.progresoMaestro.progress = 100
            maestroUnlocked = true

        }

        if (!capitanUnlocked && userPoints >= 400) {
            binding.copaCapitan.setImageResource(R.drawable.taza)
            binding.progresoCapitan.progress = 100
            capitanUnlocked = true

        }

        if (!coperoUnlocked && userPoints >= 500) {
            binding.copaCopero.setImageResource(R.drawable.taza)
            binding.progresoCopero.progress = 100
            coperoUnlocked = true

        }

        if (!estrellaUnlocked && userPoints >= 1000) {
            binding.copaEstrella.setImageResource(R.drawable.taza)
            binding.progresoEstrella.progress = 100
            estrellaUnlocked = true

        }

        if (!historiadorUnlocked && userPoints >= 1500) {
            binding.copaHistoriador.setImageResource(R.drawable.taza)
            binding.progresoHistoriador.progress = 100
            historiadorUnlocked = true

        }

        if (!reyUnlocked && userPoints >= 2000) {
            binding.copaRey.setImageResource(R.drawable.taza)
            binding.progresoRey.progress = 100
            reyUnlocked = true

        }

        if (!misticaUnlocked && userPoints >= 5000) {
            binding.copaMistica.setImageResource(R.drawable.taza)
            binding.progresoMistica.progress = 100
            misticaUnlocked = true

        }

    }

    private fun changeProfileImage(){
        binding.changeProfileImage.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_avatarProfileFragment)

        }
    }

    private fun showAchievementsText(){
        TooltipCompat.setTooltipText(binding.copaGanador, "Alcanza la cima con 100 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaInvicto, "Alcanza los 200 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaMaestro, "Alcanza la maestría con 300 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaCapitan, "Sé un líder con 400 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaCopero, "Transformate en Copero con 500 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaEstrella, "Conviértete en la estrella del juego con 1000 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaHistoriador, "Conoce la historia con 1500 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaRey, "Sé el rey indiscutible con 2000 puntos en el ranking")
        TooltipCompat.setTooltipText(binding.copaMistica, "Desbloquea la mística copera con 5000 puntos en el ranking")

    }


}












