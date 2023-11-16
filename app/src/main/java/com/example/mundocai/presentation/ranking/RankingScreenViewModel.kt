package com.example.mundocai.presentation.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.mundocai.core.Resource
import com.example.mundocai.domain.matchs.MatchsScreenRepo
import com.example.mundocai.domain.ranking.RankingScreenRepo
import kotlinx.coroutines.Dispatchers

class RankingScreenViewModel (private val repo: RankingScreenRepo): ViewModel()  {

    fun getAllRanking() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    Pair(
                        repo.getPodium(),
                        repo.getRanking()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}



class RankingScreenViewModelFactory(private val repo: RankingScreenRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RankingScreenRepo::class.java).newInstance(repo)
    }

}