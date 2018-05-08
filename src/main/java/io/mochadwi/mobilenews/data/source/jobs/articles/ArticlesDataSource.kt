package io.mochadwi.mobilenews.data.source.jobs.articles

import io.mochadwi.mobilenews.domain.model.articles.ArticlesModel

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
interface ArticlesDataSource {

    fun getArticles(callback: GetArticlesCallback)
    fun clearDisposable()

    interface GetArticlesCallback {
        fun onProductsLoad(articles: ArticlesModel)
        fun onFailed(errorMessage: String)
    }
}