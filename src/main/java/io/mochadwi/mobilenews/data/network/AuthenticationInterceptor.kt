package io.mochadwi.mobilenews.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by mochadwi on 3/15/18.
 */
class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
                .header("Authorization", "bearer $authToken")

        val request = builder.build()
        return chain.proceed(request)
    }
}