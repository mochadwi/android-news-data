package io.mochadwi.mobilenews.data.source.remote

import io.mochadwi.mobilenews.data.BaseResponse
import io.mochadwi.mobilenews.data.source.ApiService
import io.mochadwi.mobilenews.data.source.jobs.products.ProductsDataSource
import io.mochadwi.mobilenews.data.util.ScheduleUtils
import io.mochadwi.mobilenews.domain.model.Product
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
object ProductsRemoteDataSource : ProductsDataSource {

    val apiService = ApiService.createService()
    var compositeDisposable: CompositeDisposable? = null

    override fun getProducts(callback: ProductsDataSource.GetProductsCallback) {
        addDisposable(apiService.getProducts()
                .compose(ScheduleUtils.set<BaseResponse<List<Product>>>())
                .subscribe({ products ->
                    products.let {
                        callback.onProductsLoad(products.data)
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