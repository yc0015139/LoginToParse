package dev.yc.logintoparse.data.remote.request

import com.google.gson.annotations.SerializedName

data class TimeZoneRequest(
    @SerializedName("timezone") val timeZone: Int
)