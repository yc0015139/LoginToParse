package dev.yc.logintoparse.ui.updateuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.yc.logintoparse.data.repository.UserRepository

class TimeZoneUpdaterViewModelFactory(
    private val userRepository: UserRepository,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimeZoneUpdaterViewModel(userRepository) as T
    }
}