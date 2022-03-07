package dev.yc.logintoparse.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _someText = MutableLiveData<String>()
    val someText: LiveData<String> get() = _someText

    fun doSomething() {
        val data = listOf("BTC", "ETH", "XRP", "XMR", "SOL", "ADA")
        _someText.value = data.random()
    }
}