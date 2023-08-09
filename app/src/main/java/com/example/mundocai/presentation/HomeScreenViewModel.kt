package com.example.mundocai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.mundocai.core.Resource
import com.example.mundocai.domain.HomeScreenRepo
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(private val repo: HomeScreenRepo): ViewModel() {

    fun fetchLatestNews() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    NTuple6(
                        repo.getLatestMatchs(),
                        repo.getLatestNewsMain(),
                        repo.getLatestNews(),
                        repo.getLatestHistory(),
                        repo.getInvite(),
                        repo.getImages()

                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}



class HomeScreenViewModelFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }

}

data class NTuple6<T1, T2, T3, T4, T5, T6>(val t1: T1, val t2: T2, val t3: T3, val t4: T4, val t5: T5, val t6: T6)