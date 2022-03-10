package dev.yc.logintoparse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.yc.logintoparse.data.AppContainer

class MainActivity : AppCompatActivity() {
    private var _appContainer: AppContainer? = null
    val appContainer: AppContainer get() = _appContainer!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        _appContainer = (application as LtpApplication).appContainer
    }

    override fun onDestroy() {
        super.onDestroy()
        _appContainer = null
    }
}