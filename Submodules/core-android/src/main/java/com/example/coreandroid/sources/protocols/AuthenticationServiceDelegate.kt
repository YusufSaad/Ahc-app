package com.example.coreandroid.sources.protocols


/**
 * Created by ahmedsaad on 2018-03-05.
 * Copyright © 2018. All rights reserved.
 */
interface AuthenticationServiceDelegate {
    fun authenticationDidLogin(userID: Int)
    fun authenticationDidLogout(userID: Int)
}