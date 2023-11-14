package com.example.mundocai.data.remote.questions

import com.example.mundocai.data.model.Question
import com.example.mundocai.data.model.QuestionList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.*

class QuestionsScreenDataSource {

    suspend fun getQuestions(): QuestionList {
        val questionList = mutableListOf<Question>()

        val random = Random().nextInt(7) // Genera un número aleatorio entre 0 y 999

        val querySnapshot = FirebaseFirestore.getInstance().collection("questions")
            .whereGreaterThan("index", random) // Usando el campo "random"
            .orderBy("index")
            .limit(5)
            .get().await()

        for (document in querySnapshot.documents) {
            val question = document.toObject(Question::class.java)
            question?.let { questionList.add(it) }
        }

        // Si la primera consulta no devuelve suficientes preguntas
        val remainingQuestions = 5 - questionList.size
        if (remainingQuestions > 0) {
            val secondQuerySnapshot = FirebaseFirestore.getInstance().collection("questions")
                .whereLessThan("index", random) // Usando el campo "random" para otra consulta
                .orderBy("index")
                .limit(remainingQuestions.toLong())
                .get().await()

            for (document in secondQuerySnapshot.documents) {
                val question = document.toObject(Question::class.java)
                question?.let { questionList.add(it) }
            }
        }

        return QuestionList(questionList)
    }

}