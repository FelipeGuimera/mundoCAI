package com.example.mundocai.presentation.auth

import androidx.lifecycle.*
import com.example.mundocai.core.Resource
import com.example.mundocai.domain.auth.AuthRepo
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repo: AuthRepo) : ViewModel() {


    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signIn(email, password)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun signUp(email: String, password: String, username: String, profilePicture:String, points:Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signUp(email, password, username,profilePicture, points)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun signAnonymous() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.signAnonymous()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun saveUsername(username: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.saveUsername(username)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }



    class AuthViewModelFactory(private val repo: AuthRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(AuthRepo::class.java).newInstance(repo)
        }
    }

}
