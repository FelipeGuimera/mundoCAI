package com.example.mundocai.domain.questions

import com.example.mundocai.data.model.QuestionList

interface QuestionsScreenRepo {

    suspend fun getQuestions(): QuestionList
}