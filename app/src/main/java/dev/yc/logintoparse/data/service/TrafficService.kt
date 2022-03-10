package dev.yc.logintoparse.data.service

import dev.yc.logintoparse.data.remote.response.TrafficResponse
import retrofit2.Response
import retrofit2.http.GET

interface TrafficService {
    @GET("dotapp/news.json")
    suspend fun fetchTrafficNews(): Response<TrafficResponse>
}