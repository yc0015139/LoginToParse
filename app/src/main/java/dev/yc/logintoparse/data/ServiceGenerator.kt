package dev.yc.logintoparse.data

import dev.yc.logintoparse.data.remote.RemoteConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val config: RemoteConfig = RemoteConfig()
    private val serverUrl = config.url

    private val parseHeadersInterceptor = Interceptor { chain ->
        val request = chain.request().run {
            newBuilder()
                .addHeader(RemoteConfig.HEADER_PARSE_APPLICATION_ID, config.parseApplicationId)
                .build()
        }
        chain.proceed(request)
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(parseHeadersInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}