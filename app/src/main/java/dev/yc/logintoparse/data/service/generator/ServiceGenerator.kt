package dev.yc.logintoparse.data.service.generator

import dev.yc.logintoparse.data.remote.config.RemoteConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator(
    config: RemoteConfig,
    private val addOnInterceptor: Interceptor? = null,
) {
    private val serverUrl = config.url

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder().run {
        if (addOnInterceptor != null) {
            addInterceptor(addOnInterceptor)
        }
        addInterceptor(httpLoggingInterceptor)
        build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}