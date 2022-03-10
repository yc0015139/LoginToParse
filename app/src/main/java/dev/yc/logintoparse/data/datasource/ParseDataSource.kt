package dev.yc.logintoparse.data.datasource

import dev.yc.logintoparse.data.remote.ApiResult
import dev.yc.logintoparse.data.remote.LoginRequest
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
}
