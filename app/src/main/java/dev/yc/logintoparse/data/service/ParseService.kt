package dev.yc.logintoparse.data.service

import dev.yc.logintoparse.data.remote.request.LoginRequest
import dev.yc.logintoparse.data.remote.request.TimeZoneRequest
import dev.yc.logintoparse.model.ParseUser
import retrofit2.Response
import retrofit2.http.*

interface ParseService {
    @POST("api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<ParseUser>

    @PUT("api/users/{objectId}")
    suspend fun updateTimeZone(
        @Path("objectId") id: String,
        @Header("X-Parse-Session-Token") sessionToken: String,
        @Body timeZoneRequest: TimeZoneRequest,
    ): Response<Unit>
}
