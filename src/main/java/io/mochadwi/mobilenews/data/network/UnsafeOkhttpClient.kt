package io.mochadwi.mobilenews.data.network

import io.mochadwi.mobilenews.data.BuildConfig
import okhttp3.OkHttpClient
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by mochadwi on 3/14/18.
 */
class UnsafeOkhttpClient {
    companion object {
        // Create a trust manager that does not validate certificate chains
        // Install the all-trusting trust manager
        // Create an ssl socket factory with our all-trusting manager
        val unsafeOkHttpClient: OkHttpClient
            get() {
                try {
                    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                        }

                        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                            return arrayOf()
                        }
                    })
                    val sslContext = SSLContext.getInstance("SSL")
                    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                    val sslSocketFactory = sslContext.socketFactory

                    val builder = OkHttpClient.Builder()
                    builder.sslSocketFactory(sslSocketFactory)
                    builder.hostnameVerifier { hostname, session -> true }

                    val interceptor = AuthenticationInterceptor(BuildConfig.APIKEY) // add authentication here
                    if (!builder.interceptors().contains(interceptor)) {
                        builder.addInterceptor(interceptor)
                    }

                    return builder
                            .readTimeout(60, TimeUnit.SECONDS)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .addInterceptor(RESTGenerator.logging)
                            .build()
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }

            }

    }
}
