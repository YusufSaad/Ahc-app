package org.abuhuraira.app.common.extensions

import android.graphics.Paint
import android.widget.TextView

/**
 * Created by ahmedsaad on 2018-03-05.
 * Copyright © 2018. All rights reserved.
 */
var TextView.addStrikeThrough
    get() = (paintFlags and Paint.STRIKE_THRU_TEXT_FLAG) > 0
    set(value) {
        if (!value && addStrikeThrough) {
            paintFlags = paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG
        } else if (value && !addStrikeThrough) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }