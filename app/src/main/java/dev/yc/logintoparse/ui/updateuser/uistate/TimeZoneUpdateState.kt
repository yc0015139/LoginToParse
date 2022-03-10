package dev.yc.logintoparse.ui.updateuser.uistate

sealed interface TimeZoneUpdateState {
    object Success: TimeZoneUpdateState
    object Fail: TimeZoneUpdateState
}