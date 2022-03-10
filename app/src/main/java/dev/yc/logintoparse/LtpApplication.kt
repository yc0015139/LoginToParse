package dev.yc.logintoparse

import android.app.Application
import dev.yc.logintoparse.data.AppContainer

class LtpApplication : Application() {
    var appContainer = AppContainer()
}