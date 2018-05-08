package io.mochadwi.mobilenews.util

import android.content.Context
import io.mochadwi.mobilenews.data.source.jobs.products.ProductsRepository
import io.mochadwi.mobilenews.data.source.remote.ProductsRemoteDataSource

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

object Injection {

    fun providedProductRepository(context: Context) = ProductsRepository.getInstance(ProductsRemoteDataSource,
            ProductsRemoteDataSource)
}