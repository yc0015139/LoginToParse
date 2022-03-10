package dev.yc.logintoparse.ui.trafficnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.yc.logintoparse.data.repository.TrafficNewsRepository

class TrafficNewsViewModelFactory(
    private val trafficNewsRepository: TrafficNewsRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrafficNewsViewModel(trafficNewsRepository) as T
    }
}