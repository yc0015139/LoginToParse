package dev.yc.logintoparse.ui.updateuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.yc.logintoparse.data.repository.UserRepository
import dev.yc.logintoparse.ui.updateuser.uistate.TimeZoneUpdateState
import dev.yc.logintoparse.utils.livedata.SingleEvent
import dev.yc.logintoparse.utils.livedata.invoke
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TimeZoneUpdaterViewModel(
    private val repository: UserRepository,
) : ViewModel() {
    private var _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private var _success = MutableLiveData<SingleEvent>()
    val success: LiveData<SingleEvent> get() = _success

    private var _fail = MutableLiveData<SingleEvent>()
    val fail: LiveData<SingleEvent> get() = _fail

    lateinit var timeZones: List<String>
    private lateinit var timeZoneMapper: Map<String, Int>

    init {
        getEmail()
        generateTimeZoneAndMapper()
    }

    private fun getEmail() {
        _email.value = repository.getEmail()
    }

    private fun generateTimeZoneAndMapper() {
        val timeZones = mutableListOf<String>()
        val timeZoneMapper = mutableMapOf<String, Int>()
        val timeZoneRange = -12..12
        for (i in timeZoneRange) {
            val timeZone = "GMT" + (if (i >= 0) "+" else "") + i
            timeZones.add(timeZone)
            timeZoneMapper[timeZone] = i
        }

        this.timeZones = timeZones
        this.timeZoneMapper = timeZoneMapper
    }

    fun updateTimeZone(timeZone: String) {
        viewModelScope.launch {
            val valueOfTimeZone: Int = try {
                timeZoneMapper.getValue(timeZone)
            } catch (e: NoSuchElementException) {
                _fail.invoke()
                return@launch
            }

            repository.updateTimeZone(valueOfTimeZone)
                .collect {
                    when (it) {
                        TimeZoneUpdateState.Success -> _success.invoke()
                        TimeZoneUpdateState.Fail -> _fail.invoke()
                    }
                }
        }
    }
}