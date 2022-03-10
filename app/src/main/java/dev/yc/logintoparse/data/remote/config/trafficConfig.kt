package dev.yc.logintoparse.data.remote.config

data class trafficConfig(
    override val url: String = "https://tcgbusfs.blob.core.windows.net/"
): RemoteConfig
