package dev.yc.logintoparse.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.yc.logintoparse.data.repository.LoginRepository
import dev.yc.logintoparse.utils.livedata.Event
import dev.yc.logintoparse.utils.livedata.SingleEvent
import dev.yc.logintoparse.utils.livedata.invoke
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository,
) : ViewModel() {
    private var _canClick = MutableLiveData(Event(true))
    val canClick: LiveData<Event<Boolean>> get() = _canClick

    private var _login = MutableLiveData<SingleEvent>()
    val login: LiveData<SingleEvent> get() = _login

    private var _invalid = MutableLiveData<SingleEvent>()
    val invalid: LiveData<SingleEvent> get() = _invalid

    private var _empty = MutableLiveData<SingleEvent>()
    val empty: LiveData<SingleEvent> get() = _empty

    fun doLogin(account: String, password: String) {
        _canClick.value = Event(false)

        viewModelScope.launch {
            repository.login(account, password)
                .collect {
                    when (it) {
                        LoginState.Success -> _login.invoke()
                        LoginState.Invalid -> onInvalid()
                        LoginState.Empty -> onEmpty()
                    }
                }

        }
    }

    private fun onInvalid() {
        _canClick.value = Event(true)
        _invalid.invoke()
    }

    private fun onEmpty() {
        _canClick.value = Event(true)
        _empty.invoke()
    }
}