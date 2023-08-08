package com.example.mundocai.data.model

data class Invite(
    val invite_img: String = "",
    val invite_txt: String = "",
)

data class InviteList(val results: List<Invite> = listOf())