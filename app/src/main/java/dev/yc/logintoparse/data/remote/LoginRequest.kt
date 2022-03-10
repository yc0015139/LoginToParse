package dev.yc.logintoparse.data.remote

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username") val account: String,
    val password: String,
)
