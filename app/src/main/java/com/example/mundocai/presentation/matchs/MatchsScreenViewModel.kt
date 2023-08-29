package com.example.mundocai.presentation.matchs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.mundocai.core.Resource
import com.example.mundocai.domain.home.HomeScreenRepo
import com.example.mundocai.domain.matchs.MatchsScreenRepo
import com.example.mundocai.presentation.NTuple6
import kotlinx.coroutines.Dispatchers

class MatchsScreenViewModel(private val repo: MatchsScreenRepo): ViewModel()  {

    fun fetchLatestMatchs() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success((
                        repo.getLatestMatchs()

                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}



class MatchsScreenViewModelFactory(private val repo: MatchsScreenRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MatchsScreenRepo::class.java).newInstance(repo)
    }

}
