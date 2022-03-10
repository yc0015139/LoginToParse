package dev.yc.logintoparse.data.datasource

import dev.yc.logintoparse.data.remote.ApiResult
import dev.yc.logintoparse.data.service.TrafficService
import dev.yc.logintoparse.utils.ApiUtil

class TrafficDataSource(
    private val trafficService: TrafficService,
) {
    suspend fun fetchTrafficNews() = ApiUtil.executeAndParse(
        apiScope = {
            trafficService.fetchTrafficNews()
        },
        onApiSuccess = {
            val trafficNews = it.news
            ApiResult.Success(trafficNews)
        }
    )
}