package dev.yc.logintoparse.data.datasource

import dev.yc.logintoparse.data.remote.ApiResult
import dev.yc.logintoparse.data.remote.request.LoginRequest
import dev.yc.logintoparse.data.remote.request.TimeZoneRequest
import dev.yc.logintoparse.data.service.ParseService
import dev.yc.logintoparse.model.User
import dev.yc.logintoparse.utils.ApiUtil

class ParseDataSource(
    private val parseService: ParseService,
) {
    suspend fun doLogin(
        account: String,
        password: String,
    ) = ApiUtil.executeAndParse(
        apiScope = {
            val loginRequest = LoginRequest(account, password)
            parseService.login(loginRequest)
        },
        onApiSuccess = { parseUser ->
            val user = User(
                id = parseUser.id,
                username = parseUser.username,
                email = parseUser.email,
                timeZone = parseUser.timezone,
                sessionToken = parseUser.sessionToken,
            )
            ApiResult.Success(user)
        }
    )

    suspend fun updateTimeZone(
        id: String,
        sessionToken: String,
        timeZone: Int,
    ) = ApiUtil.executeAndParse(
        apiScope = {
            val timeZoneRequest = TimeZoneRequest(timeZone)
            parseService.updateTimeZone(
                id, sessionToken, timeZoneRequest
            )
        },
        onApiSuccess = {
            ApiResult.Success(it)
        }
    )
}
