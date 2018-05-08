package io.mochadwi.mobilenews.data

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

open class BaseFragment : Fragment() {

    lateinit var progressDialog: ProgressDialog
    lateinit var mActiviy: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActiviy = activity as AppCompatActivity

        initProgressDialog(activity)
    }

    /**
     * Do setup progress dialog for init
     */
    private fun initProgressDialog(context: Context) {
        progressDialog = ProgressDialog(context)
    }

    /**
     * Show this dialog when the app request to API.
     * Hide it when the opposite
     * @param mMessage
     */
    fun showProgressDialog(title: String, message: String, cancel: Boolean) {
        progressDialog.setTitle(title)
        progressDialog.setMessage(message)
        progressDialog.setCanceledOnTouchOutside(cancel)
        progressDialog.show()
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    fun showToast(message: String) {
        Toast.makeText(mActiviy, message, Toast.LENGTH_SHORT).show()
    }

}