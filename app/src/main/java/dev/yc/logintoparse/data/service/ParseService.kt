package dev.yc.logintoparse.data.service

import dev.yc.logintoparse.data.remote.LoginRequest
import dev.yc.logintoparse.model.ParseUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ParseService {
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<ParseUser>
}
