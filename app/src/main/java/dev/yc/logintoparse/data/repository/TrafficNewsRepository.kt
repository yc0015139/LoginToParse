package dev.yc.logintoparse.data.repository

import dev.yc.logintoparse.data.datasource.TrafficDataSource
import dev.yc.logintoparse.data.remote.ApiResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class TrafficNewsRepository(
    private val trafficDataSource: TrafficDataSource,
    private val dispatcher: CoroutineContext,
) {
    suspend fun fetchTrafficNews() =
        flow {
            when (val apiResult = trafficDataSource.fetchTrafficNews()) {
                is ApiResult.Success -> emit(apiResult.result)
                else -> emit(listOf())
            }
        }.flowOn(dispatcher)
}