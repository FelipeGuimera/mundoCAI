package com.example.mundocai.data.model

data class User(
    val email: String = "",
    val username: String = "",
    val profilePicture: String = "",
    val points: Int = 0
)

data class UserList(val results: List<User> = listOf())

