package org.abuhuraira.app.common.cleanBase

/**
 * Created by ahmedsaad on 2018-01-22.
 * Copyright © 2017. All rights reserved.
 */
sealed class AppModels {
    class Error(
            val title: String,
            val message: String): AppModels()
}