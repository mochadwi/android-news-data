package io.mochadwi.mobilenews.data

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * Created by irfanirawansukirman on 26/01/18.
 */

open class BaseActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    lateinit var mActiviy: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActiviy = this

        initProgressDialog(mActiviy)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()

                return true
            }
        }

        return false
    }

}