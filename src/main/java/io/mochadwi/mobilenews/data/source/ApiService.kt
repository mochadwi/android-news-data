package io.mochadwi.mobilenews.data.source

import android.graphics.Movie
import io.mochadwi.mobilenews.data.BaseApiModel
import io.mochadwi.mobilenews.data.BaseResponse
import io.mochadwi.mobilenews.data.BuildConfig
import io.mochadwi.mobilenews.domain.model.Product
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

interface ApiService {

    /**
     * Getting list of movies
     *
     * @return list of movies
     */
    @GET("3/discover/movie?api_key=1b2f29d43bf2e4f3142530bc6929d341&sort_by=popularity.desc")
    fun getMovies(): Observable<BaseApiModel<List<Movie>>>

    /**
     * Getting list of books
     *
     * @return list of books
     */
    @Headers("Content-Type: application/json")
    @GET("api/v1/books")
    fun getProducts(): Observable<BaseResponse<List<Product>>>

    /**
     * Do add a book
     *
     * @param title => book title
     * @param isbn => book code with number
     * @param publisher_name => book publisher name
     * @param author_name => book writer
     *
     * @return a book saving in server
     */
    @Multipart
    @POST("api/v1/books")
    fun addProduct(@Part("title") title: RequestBody,
                   @Part("isbn") isbn: RequestBody,
                   @Part("publisher_name") publisher_name: RequestBody,
                   @Part("author_name") author_name: RequestBody,
                   @Part url_photo: MultipartBody.Part): Observable<BaseResponse<Product>>

    companion object Factory {

        fun createService(): ApiService {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = OkHttpClient.Builder()
                    .addInterceptor(mLoggingInterceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()

            val mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mClient) //Todo comment if app release
                    .build()

            return mRetrofit.create(ApiService::class.java)
        }
    }
}