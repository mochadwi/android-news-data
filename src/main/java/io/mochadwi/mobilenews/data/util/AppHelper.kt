package io.mochadwi.mobilenews.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.View

class AppHelper {

    object ViewCustom {
        // Like animation, custom scale etc
    }

    object Const {
        val BASE_URL = "https://api-books123.herokuapp.com/"
        val PERMISSION_RESULT = 100
        val REQUEST_CAMERA = 123
        val PRODUCTADD_IMAGE_PATH_ARGS = "product_add_image_path"
        val REQUEST_BODY_TEXT_PLAIN = "text/plain"
        val REQUEST_BODY_IMAGE = "image/*"
    }

    object Preference {
        // Share preference helper
    }

    object Func {
        // String transform (date formatter, currency formatter)
    }

    object Navigator {
        fun startActivity(context: Context, page: AppCompatActivity) {
            context.startActivity(Intent(context, page::class.java))
        }

        /**
         * The `fragment` is added to the container view with id `frameId`. The operation is
         * performed by the `fragmentManager`.
         */
        fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
            supportFragmentManager.transact {
                replace(frameId, fragment)
            }
        }

        /**
         * Runs a FragmentTransaction, then calls commit().
         */
        private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
            beginTransaction().apply {
                action()
            }.commit()
        }

        fun <T : Fragment> T.withArgs(
                argsBuilder: Bundle.() -> Unit): T =
                this.apply {
                    arguments = Bundle().apply(argsBuilder)
                }

        fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
            setSupportActionBar(findViewById(toolbarId))
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.run {
                action()
            }
        }

        fun View.showSnackbar(snackbarText: String, timeLength: Int) {
            Snackbar.make(this, snackbarText, timeLength).show()
        }

        /**
         * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
         */
        fun View.setupSnackbar(lifecycleOwner: LifecycleOwner,
                               snackbarMessageLiveEvent: SingleLiveEvent<Int>, timeLength: Int) {
            snackbarMessageLiveEvent.observe(lifecycleOwner, Observer {
                it.let { showSnackbar(context.getString(it!!), timeLength) }
            })
        }
    }
}