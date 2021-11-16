package org.abuhuraira.app.common.protocols

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.EditText
import android.view.inputmethod.InputMethodManager
import com.google.android.youtube.player.YouTubeBaseActivity
import org.abuhuraira.app.R
import org.abuhuraira.app.common.controls.SpinnerDialogFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by ahmedsaad on 2018-01-20.
 * Copyright © 2017. All rights reserved.
 */
interface BaseInterface {
    var spinner: SpinnerDialogFragment?
    val baseActivity: Activity?
        get() {
            return when {
                this is Fragment -> this.activity
                this is AppCompatActivity -> this
                this is YouTubeBaseActivity -> this
                else -> null
            }
        }

    fun showSpinner(message: String = "") {
        spinner = SpinnerDialogFragment.newInstance(message)
        spinner?.show(baseActivity?.fragmentManager, "spinner")
    }

    fun hideSpinner() {
        if(spinner?.showsDialog == true)
            spinner?.dismiss()
    }

    /**
    Display an alert action in a convenient way.
     **/
    fun present(title: String, message: String? = null,
                includeCancelAction: Boolean = false,
                customView: View? = null,
                positiveHandler: (DialogInterface, Int) -> Unit = {_, _ ->}) {

        baseActivity?.apply {
            this.runOnUiThread {
                val alert = AlertDialog.Builder(this)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(includeCancelAction)
                        .setPositiveButton("OK", positiveHandler)

                if (includeCancelAction) {
                    alert.setNegativeButton("Cancel"){_, _ ->}
                }

                if (customView != null) {
                    alert.setView(customView)
                }

                alert.show()
            }
        }
    }

    fun showSnackBar(message: String,
                     length: Int = Snackbar.LENGTH_LONG,
                     messageColor: Int? = null) {

        hideSpinner()

        baseActivity?.apply {
            this.runOnUiThread {
                val snackbar = Snackbar.make(findViewById(android.R.id.content), message, length)

                snackbar.setAction(R.string.hide_snackbar) {
                    snackbar.dismiss()
                }

                val snackbarView = snackbar.view
                val textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
                textView.setTextColor(messageColor ?: ContextCompat.getColor(this, android.R.color.holo_green_light))

                snackbar.show()

            }
        }
    }

    fun hideActionBar() {
        baseActivity?.actionBar?.hide()
    }

    fun hideTabs() {
        baseActivity?.tabsView?.visibility = View.GONE
    }

    fun showTabs() {
        baseActivity?.tabsView?.visibility = View.VISIBLE
    }


    /**
     * Hide keyboard.
     */
    fun hideKeyboard() {
        val inputMethodManager = baseActivity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = baseActivity?.currentFocus
        if (view == null) {
            if (inputMethodManager.isAcceptingText)
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0)
        } else {
            (view as? EditText)?.setText(view.text.toString()) // reset edit text bug on some keyboards bug
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0)
        }
    }
}