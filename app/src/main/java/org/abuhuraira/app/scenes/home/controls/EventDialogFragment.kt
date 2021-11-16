package org.abuhuraira.app.scenes.home.controls

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.util.DisplayMetrics
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_dialog_event.*
import kotlinx.android.synthetic.main.fragment_dialog_event.view.*
import org.abuhuraira.app.R
import org.abuhuraira.app.common.extensions.isHidden
import org.abuhuraira.app.scenes.home.common.HomeModels
import java.lang.ref.WeakReference


/**
* Created by ahmedsaad on 2016-03-14.
* Copyright © 2017 Jiffy Inc. All rights reserved.
*/
class EventDialogFragment : DialogFragment() {

    var delegate: WeakReference<EventDelegate?>? = null
    var viewModel: HomeModels.EventViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NORMAL
        val theme = 0
        setStyle(style, theme)
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceWidth = displayMetrics.widthPixels
        containerView.layoutParams.width = deviceWidth / 100 * 95
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dialog.setCanceledOnTouchOutside(true)

        val rootView = inflater.inflate(R.layout.fragment_dialog_event, container, false)

        Glide.with(rootView.context).load(viewModel?.imageURL)
                .apply(RequestOptions()
                        .placeholder(R.drawable.maintenance))
                .into(rootView.imageView)


        rootView.detailsButton.isHidden = viewModel?.detailURL.isNullOrBlank()

        rootView.detailsButton.setOnClickListener {
            delegate?.get()?.viewEventDetails(viewModel?.detailURL)
        }

        return rootView
    }

    companion object {

        /**
         * Create a new instance of CreditCardDialogFragment
         */
        fun newInstance(viewModel: HomeModels.EventViewModel? = null,
                        delegate: WeakReference<EventDelegate?>? = null): EventDialogFragment {
            val fragment = EventDialogFragment()
            fragment.viewModel = viewModel
            fragment.delegate = delegate
            fragment.isCancelable = true

            return fragment
        }
    }
}

interface EventDelegate {
    fun viewEventDetails(url: String?)
}
