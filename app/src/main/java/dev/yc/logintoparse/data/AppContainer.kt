package dev.yc.logintoparse.data

import dev.yc.logintoparse.data.datasource.ParseDataSource
import dev.yc.logintoparse.data.datasource.TrafficDataSource
import dev.yc.logintoparse.data.remote.config.ParseConfig
import dev.yc.logintoparse.data.remote.config.trafficConfig
import dev.yc.logintoparse.data.repository.LoginRepository
import dev.yc.logintoparse.data.repository.TrafficNewsRepository
import dev.yc.logintoparse.data.service.ParseService
import dev.yc.logintoparse.data.service.TrafficService
import dev.yc.logintoparse.data.service.generator.ServiceGenerator
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor

class AppContainer {
    private val parseConfig = ParseConfig()
    private val parseHeadersInterceptor = Interceptor { chain ->
        val request = chain.request().run {
            newBuilder()
                .addHeader(ParseConfig.HEADER_PARSE_APPLICATION_ID, parseConfig.parseApplicationId)
                .build()
        }
        chain.proceed(request)
    }

    private val parseDataSource = ParseDataSource(
        parseService = ServiceGenerator(
            config = parseConfig,
            addOnInterceptor = parseHeadersInterceptor,
        ).createService(ParseService::class.java)
    )
    private val userDataManager = UserDataManager()
    val loginRepository = LoginRepository(
        parseDataSource,
        userDataManager,
        dispatcher = Dispatchers.IO,
    )

    private val trafficConfig = trafficConfig()
    private val trafficDataSource = TrafficDataSource(
        trafficService = ServiceGenerator(
            config = trafficConfig
        ).createService(TrafficService::class.java)
    )
    val trafficNewsRepository = TrafficNewsRepository(
        trafficDataSource,
        dispatcher = Dispatchers.IO,
    )
}