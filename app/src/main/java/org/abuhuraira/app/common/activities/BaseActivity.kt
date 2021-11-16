package org.abuhuraira.app.common.activities

import android.support.v7.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeBaseActivity
import org.abuhuraira.app.common.controls.SpinnerDialogFragment
import org.abuhuraira.app.common.protocols.BaseInterface


/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */
open class BaseActivity: AppCompatActivity(), BaseInterface {
    override var spinner: SpinnerDialogFragment? = null
}