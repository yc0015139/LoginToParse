package dev.yc.logintoparse.utils

import dev.yc.logintoparse.data.remote.ApiResult
import retrofit2.Response

object ApiUtil {
    suspend inline fun <R, T> executeAndParse(
        noinline apiScope: suspend () -> Response<R>,
        onApiSuccess: (R) -> ApiResult<T>,
    ): ApiResult<T> {
        val data = try {
            apiScope.invoke()
        } catch (e: Exception) {
            return ApiResult.Exception
        }

        if (!data.isSuccessful) {
            return ApiResult.Error(data.code())
        }

        val body = data.body() ?: return ApiResult.Exception
        return onApiSuccess.invoke(body)
    }

    const val BAD_REQUEST = 400
    const val NOT_FOUND = 404
}