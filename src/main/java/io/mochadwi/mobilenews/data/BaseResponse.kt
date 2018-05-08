package io.mochadwi.mobilenews.data

/**
 * Created by irfanirawansukirman on 22/03/18.
 */
data class BaseResponse<T>(val status_code: Int, val message: String, val data: T)