package io.mochadwi.mobilenews.data.source.jobs.products

import io.mochadwi.mobilenews.domain.model.Product

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
interface ProductsDataSource {

    fun getProducts(callback: GetProductsCallback)
    fun clearDisposable()

    interface GetProductsCallback {
        fun onProductsLoad(products: List<Product>)
        fun onFailed(errorMessage: String)
    }
}