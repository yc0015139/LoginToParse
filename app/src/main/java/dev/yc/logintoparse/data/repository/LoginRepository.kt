package dev.yc.logintoparse.data.repository

import dev.yc.logintoparse.data.datasource.ParseDataSource
import dev.yc.logintoparse.data.remote.ApiResult
import dev.yc.logintoparse.ui.login.LoginState
import dev.yc.logintoparse.utils.ApiUtil
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class LoginRepository(
    private val parseDataSource: ParseDataSource,
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineContext,
) {
    suspend fun login(account: String, password: String) =
        flow {
            when (val apiResult = parseDataSource.doLogin(account, password)) {
                is ApiResult.Success -> {
                    userRepository.user = apiResult.result
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
}