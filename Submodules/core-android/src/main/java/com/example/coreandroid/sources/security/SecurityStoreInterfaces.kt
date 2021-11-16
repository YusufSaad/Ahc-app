package com.example.coreandroid.sources.security

import com.example.coreandroid.sources.enums.SecurityProperty
import org.json.JSONObject

/**
 * Created by ahmedsaad on 2017-11-10.
 * Copyright © 2017. All rights reserved.
 */

interface SecurityStore {
    fun get(key: SecurityProperty): String?
    fun set(key: SecurityProperty, value: String?): Boolean
    fun delete(key: SecurityProperty): Boolean
    fun clear()
}

abstract class SecurityWorkerType: SecurityStore {
    abstract fun set(token: String, email: String, password: String): Boolean

    abstract fun jwt(payload: JSONObject): String?

    abstract fun createKey(alias: String)
    abstract fun deleteKey(alias: String)
    abstract fun encrypt(value: String?): String?
    abstract fun decrypt(value: String?): String?
}