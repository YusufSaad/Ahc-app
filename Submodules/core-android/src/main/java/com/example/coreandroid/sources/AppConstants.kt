package com.example.coreandroid.sources

import com.example.coreandroid.sources.enums.Environment
import java.util.*
import kotlin.experimental.xor
import kotlin.text.Charsets.UTF_8

/**
 * Created by ahmedsaad on 2018-01-03.
 * Copyright © 2017. All rights reserved.
 */
object NSObject
object NSString

interface ConstantsType {
    val jwtSecretKey: String
    val googlePlacesAPIKey: String
    val youtubeAPIKey: String
    val firebaseApiKey: String
    val firebaseApplicationID: String
    val firebaseDatabaseUrl: String
    val firebaseProjectID: String
    val firebaseStorageBucket: String
    val logDNAKey: String
}

class AppConstants: ConstantsType {
    override val jwtSecretKey: String
        get() = AppConstants.jwtSecretKey
    override val googlePlacesAPIKey: String
        get() = AppConstants.googlePlacesAPIKey
    override val youtubeAPIKey: String
        get() = AppConstants.youtubeAPIKey
    override val firebaseApiKey: String
        get() = AppConstants.firebaseApiKey
    override val firebaseApplicationID: String
        get() = AppConstants.firebaseApplicationID
    override val firebaseDatabaseUrl: String
        get() = AppConstants.firebaseDatabaseUrl
    override val firebaseProjectID: String
        get() = AppConstants.firebaseProjectID
    override val firebaseStorageBucket: String
        get() = AppConstants.firebaseStorageBucket
    override val logDNAKey: String
        get() = AppConstants.logDNAKey

    companion object {

        val jwtSecretKey by lazy {
            unobfuscate(key = byteArrayOf(0)
            )
        }

        val googlePlacesAPIKey by lazy {
            unobfuscate(key = byteArrayOf(0)
            )
        }

        val youtubeAPIKey by lazy {
            unobfuscate(key = byteArrayOf(26, 7, 41, 46, 49, 19, 33, 57, 59, 125, 86, 40, 29, 12,
                    33, 71, 26, 33, 82, 22, 50, 43, 29, 46, 51, 3, 46, 52, 25, 25, 77, 13, 20, 33,
                    4, 17, 56, 95, 34)
            )
        }
        
        val firebaseApiKey by lazy {
            unobfuscate(key = when (Environment.mode) {
                Environment.DEVELOPMENT -> byteArrayOf(0)
                Environment.PRODUCTION -> byteArrayOf(0)
            })
        }

        val firebaseApplicationID by lazy {
            unobfuscate(key = when (Environment.mode) {
                Environment.DEVELOPMENT -> byteArrayOf(0)
                Environment.PRODUCTION -> byteArrayOf(0)
            })
        }

        val firebaseDatabaseUrl by lazy {
            unobfuscate(key = when (Environment.mode) {
                Environment.DEVELOPMENT -> byteArrayOf(0)
                Environment.PRODUCTION -> byteArrayOf(0)
            })
        }

        val firebaseProjectID by lazy {
            unobfuscate(key = when (Environment.mode) {
                Environment.DEVELOPMENT -> byteArrayOf(0)
                Environment.PRODUCTION -> byteArrayOf(0)
            })
        }

        val firebaseStorageBucket by lazy {
            unobfuscate(key = when (Environment.mode) {
                Environment.DEVELOPMENT -> byteArrayOf(0)
                Environment.PRODUCTION -> byteArrayOf(0)
            })
        }

        val logDNAKey by lazy {
            unobfuscate(byteArrayOf(0))
        }

        /// The salt used to obfuscate and reveal the string.
        private val secretSalt = {
            Arrays.toString(arrayOf(NSObject::class.java.simpleName,
                        NSString::class.java.simpleName))
        }()

        /**
        This method obfuscates the string passed in using the salt
        that was used when the Obfuscator was initialized.
        https://gist.github.com/DejanEnspyra/80e259e3c9adf5e46632631b49cd1007

        - parameter string: the string to obfuscate

        - returns: the obfuscated string in a byte array
         */
        fun obfuscate(string: String): String {
            val text = string.toByteArray(UTF_8)
            val cipher = AppConstants.secretSalt.toByteArray(UTF_8)
            val length = cipher.count()
            val encrypted = mutableListOf<Byte>()

            text.mapIndexedTo(encrypted) { index, value ->
                value xor cipher[index % length]
            }

            return encrypted.joinToString()
        }

        /**
        This method reveals the original string from the obfuscated
        byte array passed in. The salt must be the same as the one
        used to encrypt it in the first place.

        - parameter key: the byte array to reveal

        - returns: the original string
         */
        fun unobfuscate(key: ByteArray): String {
            val cipher = AppConstants.secretSalt.toByteArray(UTF_8)
            val length = cipher.count()
            val decrypted = mutableListOf<Byte>()

            key.mapIndexedTo(decrypted) { index, value ->
                value xor cipher[index % length]
            }

            return String(decrypted.toByteArray(), charset = UTF_8)
        }
    }
}