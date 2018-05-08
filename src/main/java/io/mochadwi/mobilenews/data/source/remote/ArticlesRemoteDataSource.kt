package io.mochadwi.mobilenews.data.source.remote

import io.mochadwi.mobilenews.data.BuildConfig
import io.mochadwi.mobilenews.data.network.RESTClient
import io.mochadwi.mobilenews.data.network.RESTGenerator
import io.mochadwi.mobilenews.data.source.jobs.articles.ArticlesDataSource
import io.mochadwi.mobilenews.data.util.ScheduleUtils
import io.mochadwi.mobilenews.domain.model.articles.ArticlesModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
object ArticlesRemoteDataSource : ArticlesDataSource {

    val apiService = RESTGenerator.createService(BuildConfig.BASEURL, RESTClient::class.java)
    var compositeDisposable: CompositeDisposable? = null

    override fun getArticles(callback: ArticlesDataSource.GetArticlesCallback) {

        addDisposable(
                apiService.getTopStory("id", "")
                        .compose(ScheduleUtils.set<ArticlesModel>())
                        .subscribe({ articles ->
                            articles.let {
                                callback.onProductsLoad(articles)
                            }
                        }, {
                            callback.onFailed(it.message!!)
                        })

        )
    }

    override fun clearDisposable() {
        clearSubscribe()
    }

    private fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            compositeDisposable!!.add(disposable)
        }
    }

    private fun clearSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
        }
    }
}