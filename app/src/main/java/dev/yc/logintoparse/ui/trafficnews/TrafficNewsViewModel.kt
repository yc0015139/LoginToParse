package dev.yc.logintoparse.ui.trafficnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.yc.logintoparse.data.repository.TrafficNewsRepository
import dev.yc.logintoparse.model.TrafficInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TrafficNewsViewModel(
    private val trafficNewsRepository: TrafficNewsRepository,
) : ViewModel() {

    private val _trafficNews = MutableLiveData<List<TrafficInfo>>()
    val trafficNews: LiveData<List<TrafficInfo>> get() = _trafficNews

    init {
        fetchTrafficNews()
    }

    private fun fetchTrafficNews() {
        viewModelScope.launch {
            trafficNewsRepository.fetchTrafficNews()
                .collect {
                    _trafficNews.value = it
                }
        }
    }
}