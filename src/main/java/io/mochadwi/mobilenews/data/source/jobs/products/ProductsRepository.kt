package io.mochadwi.mobilenews.data.source.jobs.products

import io.mochadwi.mobilenews.domain.model.Product

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
open class ProductsRepository(val remoteDataSource: ProductsDataSource,
                              val localDataSource: ProductsDataSource) : ProductsDataSource {

    override fun getProducts(callback: ProductsDataSource.GetProductsCallback) {
        remoteDataSource.getProducts(object : ProductsDataSource.GetProductsCallback {
            override fun onProductsLoad(products: List<Product>) {
                callback.onProductsLoad(products)
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

        private var INSTANCE: ProductsRepository? = null

        @JvmStatic
        fun getInstance(productsRemoteDataSource: ProductsDataSource,
                        productsLocalDataSource: ProductsDataSource): ProductsRepository {
            return INSTANCE ?: ProductsRepository(productsRemoteDataSource, productsLocalDataSource)
                    .apply { INSTANCE = this }
        }
    }
}