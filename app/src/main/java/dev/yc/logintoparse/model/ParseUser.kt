package dev.yc.logintoparse.model

import com.google.gson.annotations.SerializedName

data class ParseUser(
    @SerializedName("objectId") val id: String,
    val username: String,
    val code: String,
    @SerializedName("isVerifiedReportEmail") val isEmailVerify: Boolean,
    @SerializedName("reportEmail") val email: String,
    val created: String,
    val updatedAt: String,
    val timezone: Int,
    val parameter: Int,
    val number: Int,
    val phone: String,
    @SerializedName("timeZone") val timeZoneOnGMT: String,
    val timone: String,
    @SerializedName("ACL") val acl: Map<String, ParseAcl>,
    val sessionToken: String,
)