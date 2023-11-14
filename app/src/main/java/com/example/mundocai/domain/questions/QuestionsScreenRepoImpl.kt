package com.example.mundocai.domain.questions

import com.example.mundocai.data.model.QuestionList
import com.example.mundocai.data.remote.matchs.MatchsScreenDataSource
import com.example.mundocai.data.remote.questions.QuestionsScreenDataSource
import com.example.mundocai.domain.matchs.MatchsScreenRepo

class QuestionsScreenRepoImpl (private val dataSource: QuestionsScreenDataSource): QuestionsScreenRepo {

    override suspend fun getQuestions(): QuestionList = dataSource.getQuestions()
}