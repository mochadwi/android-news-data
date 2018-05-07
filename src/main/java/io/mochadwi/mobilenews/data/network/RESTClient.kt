package io.mochadwi.mobilenews.data.network

import io.mochadwi.mobilenews.domain.model.articles.ArticlesModel
import io.mochadwi.mobilenews.domain.model.news_source.NewsSourceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mochadwi on 3/13/18.
 */
interface RESTClient {

    @GET("sources")
    fun getRecommendedSources(): Call<NewsSourceModel>

    @GET("top-headlines")
    fun getArticles(@Query("sources") sources: String): Call<ArticlesModel>
}