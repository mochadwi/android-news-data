package io.mochadwi.mobilenews.data.source.jobs.articles

import io.mochadwi.mobilenews.domain.model.articles.ArticlesModel

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
open class ArticlesRepository(val remoteDataSource: ArticlesDataSource,
                              val localDataSource: ArticlesDataSource) : ArticlesDataSource {

    override fun getArticles(callback: ArticlesDataSource.GetArticlesCallback) {

        remoteDataSource.getArticles(object : ArticlesDataSource.GetArticlesCallback {
            override fun onProductsLoad(articles: ArticlesModel) {
                callback.onProductsLoad(articles)
            }

            override fun onFailed(errorMessage: String) {
                callback.onFailed(errorMessage)
            }
        })
    }

    override fun clearDisposable() {

        remoteDataSource.clearDisposable()
    }

    companion object {

        private var INSTANCE: ArticlesRepository? = null

        @JvmStatic
        fun getInstance(articlesRemoteDataSource: ArticlesDataSource,
                        articlesLocalDataSource: ArticlesDataSource): ArticlesRepository {
            return INSTANCE ?: ArticlesRepository(articlesRemoteDataSource, articlesLocalDataSource)
                    .apply { INSTANCE = this }
        }
    }
}