package com.example.coreandroid.sources.enums

import com.example.coreandroid.BuildConfig

/**
 * Created by ahmedsaad on 2017-11-16.
 * Copyright © 2017. All rights reserved.
 */

enum class Environment {
    DEVELOPMENT,
    PRODUCTION;
    companion object {
        var mode: Environment = {
            if (BuildConfig.DEBUG)
                DEVELOPMENT
            else
                PRODUCTION
        }()
    }
}