package io.mochadwi.mobilenews.data.network

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.mochadwi.mobilenews.data.BuildConfig
import io.mochadwi.mobilenews.domain.model.news_source.SourcesItemSerializer
import io.realm.RealmObject
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


/**
 * Created by mochadwi on 3/13/18.
 */
class RESTGenerator {

    companion object {
        val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        fun getRetrofitClient(): Retrofit {
            return buildClient(BuildConfig.BASEURL)
        }

        fun getRetrofitClient(url: String): Retrofit {
            return buildClient(url)
        }

        private fun buildClient(url: String): Retrofit {

            // Create Gson builder
            val gsonBuilder = GsonBuilder()

            gsonBuilder.setExclusionStrategies(object : ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    return f.declaringClass == RealmObject::class.java
                }

                override fun shouldSkipClass(clazz: Class<*>): Boolean {
                    return false
                }
            })

            // Register adapter to builder
            try {
                gsonBuilder.registerTypeAdapter(
                        Class.forName(
                                "io.realm.io_mochadwi_mobilenews_news_source_model_SourcesItemRealmProxy"),
                        SourcesItemSerializer())
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }


            // Create gson
            val gson = gsonBuilder.create()

            return Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(url)
                    .client(UnsafeOkhttpClient.unsafeOkHttpClient)
                    .build()
        }

        fun <S> createService(url: String, serviceClass: Class<S>): S {

            return buildClient(url).create(serviceClass)
        }

    }
}