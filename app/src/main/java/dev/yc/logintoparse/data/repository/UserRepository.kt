package dev.yc.logintoparse.data.repository

import dev.yc.logintoparse.data.UserDataManager
import dev.yc.logintoparse.data.datasource.ParseDataSource
import dev.yc.logintoparse.data.remote.ApiResult
import dev.yc.logintoparse.ui.login.uistate.LoginState
import dev.yc.logintoparse.ui.updateuser.uistate.TimeZoneUpdateState
import dev.yc.logintoparse.utils.ApiUtil
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class UserRepository(
    private val parseDataSource: ParseDataSource,
    private val userDataManager: UserDataManager,
    private val dispatcher: CoroutineContext,
) {
    suspend fun login(account: String, password: String) =
        flow {
            when (val apiResult = parseDataSource.doLogin(account, password)) {
                is ApiResult.Success -> {
                    userDataManager.user = apiResult.result
                    emit(LoginState.Success)
                }
                is ApiResult.Error -> {
                    when (apiResult.code) {
                        ApiUtil.BAD_REQUEST -> emit(LoginState.Empty)
                        ApiUtil.NOT_FOUND -> emit(LoginState.Invalid)
                        else -> emit(LoginState.Invalid)
                    }
                }
                ApiResult.Exception -> emit(LoginState.Invalid)
            }
        }.flowOn(dispatcher)

    suspend fun updateTimeZone(
        timeZone: Int,
    ) = flow {
        userDataManager.user?.run {
            val apiResult = parseDataSource.updateTimeZone(
                id, sessionToken, timeZone
            )
            when (apiResult) {
                is ApiResult.Success -> emit(TimeZoneUpdateState.Success)
                else -> emit(TimeZoneUpdateState.Fail)
            }
        }
    }.flowOn(dispatcher)
}