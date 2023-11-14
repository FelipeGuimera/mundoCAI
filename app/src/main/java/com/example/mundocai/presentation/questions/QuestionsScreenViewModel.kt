package com.example.mundocai.presentation.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.mundocai.core.Resource
import com.example.mundocai.domain.questions.QuestionsScreenRepo
import kotlinx.coroutines.Dispatchers

class QuestionsScreenViewModel(private val repo: QuestionsScreenRepo) : ViewModel() {

    fun fetchQuestions() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    (repo.getQuestions())
                )
            )

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }


}

class QuestionsScreenViewModelFactory(private val repo: QuestionsScreenRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(QuestionsScreenRepo::class.java).newInstance(repo)
    }
}