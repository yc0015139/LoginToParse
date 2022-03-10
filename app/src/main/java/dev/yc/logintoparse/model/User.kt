package dev.yc.logintoparse.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val timeZone: Int,
    val sessionToken: String,
)
