package dev.yc.logintoparse.data.remote

data class RemoteConfig(
    val url: String = "https://watch-master-staging.herokuapp.com/",
    val parseApplicationId: String = "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD",
) {
    companion object {
        const val HEADER_PARSE_APPLICATION_ID = "X-Parse-Application-Id"
    }
}