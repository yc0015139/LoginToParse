package dev.yc.logintoparse.data.remote.response

import com.google.gson.annotations.SerializedName
import dev.yc.logintoparse.model.TrafficInfo

data class TrafficResponse(
    val updateTime: String,
    @SerializedName("News") val news: List<TrafficInfo>
)