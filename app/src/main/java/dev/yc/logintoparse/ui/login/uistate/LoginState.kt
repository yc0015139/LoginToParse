package dev.yc.logintoparse.ui.login.uistate

sealed interface LoginState {
    object Success: LoginState
    object Invalid: LoginState
    object Empty: LoginState
}