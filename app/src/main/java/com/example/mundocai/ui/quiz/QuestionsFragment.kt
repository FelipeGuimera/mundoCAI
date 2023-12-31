package com.example.mundocai.ui.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mundocai.R
import com.example.mundocai.core.Resource
import com.example.mundocai.data.model.Question
import com.example.mundocai.data.remote.questions.QuestionsScreenDataSource
import com.example.mundocai.databinding.FragmentQuestionsBinding
import com.example.mundocai.domain.questions.QuestionsScreenRepoImpl
import com.example.mundocai.presentation.questions.QuestionsScreenViewModel
import com.example.mundocai.presentation.questions.QuestionsScreenViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore

class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private lateinit var binding: FragmentQuestionsBinding

    private val viewModel by viewModels<QuestionsScreenViewModel> {
        QuestionsScreenViewModelFactory(
            QuestionsScreenRepoImpl(
                QuestionsScreenDataSource()
            )
        )
    }

    private lateinit var timer: CountDownTimer
    private var answerSelected: Boolean = false
    private var totalTime = 25000L // 25 segundos
    private var interval = 1000L // Intervalo de tick
    private var questions: List<Question> = emptyList()
    private var index: Int = 0
    private var correctAnswers: Int = 0
    private var points: Int = 0
    private lateinit var question: Question
    private val handler = Handler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestionsBinding.bind(view)





        viewModel.fetchQuestions().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Success -> {

                    binding.progressBar.visibility = View.GONE
                    // Guarda las preguntas en la variable de instancia del fragmento
                    questions = result.data.results

                    // Llama a la función para configurar la primera pregunta
                    setNextQuestion()

                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })


        binding.option1.setOnClickListener { onClick(it) }
        binding.option2.setOnClickListener { onClick(it) }
        binding.option3.setOnClickListener { onClick(it) }
        binding.option4.setOnClickListener { onClick(it) }

        backQuestions()
        backPressed()

    }

    private fun resetTimer() {
        val totalTime = 25000L // 25 segundos
        val interval = 1000L // Intervalo de cada tick

        // Establecer el máximo valor del ProgressBar en función del tiempo total y el intervalo
        binding.pbTimer.max = (totalTime / interval).toInt()

        timer = object : CountDownTimer(totalTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                // Calcular el progreso restante del temporizador
                val progress = (millisUntilFinished / interval).toInt()
                binding.pbTimer.progress = progress

                // Actualizar el texto del temporizador
                binding.timer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // Completar el progreso del ProgressBar al finalizar el temporizador
                binding.pbTimer.progress = binding.pbTimer.max

                // Lógica al finalizar el temporizador
                reset()
                index++
                setNextQuestion()
            }
        }.start()
    }


    private fun setNextQuestion() {

        if (index < questions.size) {
            binding.questionCounter.text = String.format("%d/%d", (index + 1), questions.size)
            question = questions[index]
            binding.question.text = question.question
            binding.option1.text = question.option1
            binding.option2.text = question.option2
            binding.option3.text = question.option3
            binding.option4.text = question.option4


            reset()
            resetTimer()

        } else {

            val action = QuestionsFragmentDirections.actionQuestionsFragmentToResultsFragment(
                correctAnswers,
                points,
            )
            findNavController().navigate(action)

        }
    }

    private fun showAnswer() {
        when (question.answer) {
            binding.option1.text.toString() -> binding.option1.setBackgroundResource(R.drawable.option_correct)
            binding.option2.text.toString() -> binding.option2.setBackgroundResource(R.drawable.option_correct)
            binding.option3.text.toString() -> binding.option3.setBackgroundResource(R.drawable.option_correct)
            binding.option4.text.toString() -> binding.option4.setBackgroundResource(R.drawable.option_correct)
        }
    }

    private fun checkAnswer(textView: TextView) {
        if (!answerSelected) {
            answerSelected = true // Marcar que se ha seleccionado una respuesta
            val selectedAnswer = textView.text.toString()
            if (selectedAnswer == question.answer) {
                correctAnswers++
                points = correctAnswers * 5
                textView.setBackgroundResource(R.drawable.option_correct)
            } else {
                showAnswer()
                textView.setBackgroundResource(R.drawable.option_wrong)
            }
        }
    }

    private fun reset() {
        binding.option1.setBackgroundResource(R.drawable.option_unselected)
        binding.option2.setBackgroundResource(R.drawable.option_unselected)
        binding.option3.setBackgroundResource(R.drawable.option_unselected)
        binding.option4.setBackgroundResource(R.drawable.option_unselected)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.option1, R.id.option2, R.id.option3, R.id.option4 -> {
                timer.cancel()
                val selected = view as TextView
                checkAnswer(selected)

                view.isEnabled = false // Deshabilitar la opción seleccionada

                Handler().postDelayed({
                    answerSelected =
                        false // Permitir seleccionar otra respuesta después del retraso
                    view.isEnabled = true // Habilitar la opción seleccionada nuevamente
                    index++ // Incrementar el índice para pasar a la siguiente pregunta
                    setNextQuestion() // Mostrar la siguiente pregunta
                }, 2000) // Delay de 2 segundos (2000 milisegundos)
            }
        }
    }

    private fun backQuestions() {
        binding.crossQuestions.setOnClickListener {
            val exitDialog = ExitQuestionDialog()
            exitDialog.show(childFragmentManager, "ExitQuestionDialog")

        }
    }


    private fun backPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val exitDialog = ExitQuestionDialog()
            exitDialog.show(childFragmentManager, "ExitQuestionDialog")
        }
    }


}
