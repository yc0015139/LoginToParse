package dev.yc.logintoparse.data.repository

import dev.yc.logintoparse.ui.login.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class LoginRepository(
    private val dispatcher: CoroutineContext
) {
    suspend fun login(account: String, password: String) =
        flow<LoginState> {
            delay(1500)
            emit(LoginState.Invalid)
        }.flowOn(dispatcher)
}