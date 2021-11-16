package com.example.coreandroid.sources.security

import android.content.Context
import android.os.Build
import java.security.*
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.math.BigInteger
import java.util.*
import javax.security.auth.x500.X500Principal
import java.io.ByteArrayOutputStream
import javax.crypto.*
import android.util.Base64
import com.example.coreandroid.sources.ConstantsType
import com.example.coreandroid.sources.enums.SecurityProperty
import com.example.coreandroid.sources.enums.SecurityProperty.*
import org.json.JSONObject
import java.io.ByteArrayInputStream
import javax.crypto.spec.SecretKeySpec

/**
 * Created by ahmedsaad on 2017-11-10.
 * Copyright © 2017. All rights reserved.
 */

class SecurityWorker(val context: Context?,
                     val store: SecurityStore,
                     val constants: ConstantsType) : SecurityWorkerType() {

    companion object {
        private val ALIAS = "USER_ALIAS"
        private val ANDROID_KEY_STORE = "AndroidKeyStore"
        private val TRANSFORMATION = "RSA/ECB/PKCS1Padding"
        private var JWTCached: String? = null
    }

    private val keyStore: KeyStore

    private fun getKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
        keyStore.load(null)
        return keyStore
    }

    init {
        keyStore = getKeyStore()
    }

    /// Retrieves the text value from the keychain that corresponds to the given key.
    ///
    /// - Parameter key: The key that is used to read the preference item.
    override fun get(key: SecurityProperty): String? {
        if (key == JWT && SecurityWorker.JWTCached != null) {
            return SecurityWorker.JWTCached
        }

        val value = decrypt(store.get(key))

        if (key == JWT) {
            SecurityWorker.JWTCached = value
        }

        return value
    }

    /// Stores the text value in the keychain item under the given key.
    ///
    /// - Parameters:
    ///   - value: Text string to be written to the preference.
    ///   - key: Key under which the text value is stored in the preference.
    /// - Returns: True if the item was successfully set.
    override fun set(key: SecurityProperty, value: String?): Boolean  {
        if (key == JWT) {
            SecurityWorker.JWTCached = value
        }

        return store.set(key, value = encrypt(value))
    }

    /// Deletes the single keychain item specified by the key.
    ///
    /// - Parameter key: The key that is used to delete the preference item.
    /// - Returns: True if the item was successfully deleted.
    override fun delete(key: SecurityProperty): Boolean {
        if (key == JWT) {
            SecurityWorker.JWTCached = null
        }

        return store.delete(key)
    }

    /// Removes all the preference items.
    override fun clear() {
        SecurityWorker.JWTCached = null
        store.clear()
    }

    /// Generates and stores the JWT token value in the preference item.
    ///
    /// - Returns: True if the item was successfully set.
    override fun set(token: String, email: String, password: String): Boolean {
        val payload = JSONObject().put("authentication_token", token).put("email", email)
        val jwt = jwt(payload = payload) ?: return false

        return set(key = JWT, value = jwt)
                && set(key = TOKEN, value = token)
                && set(key = EMAIL, value = email)
                && set(key = PASSWORD, value = password)
    }

    /// Generates the JWT for the given payload.
    ///
    /// - Parameter payload: A payload of keys and values.
    /// - Returns: The generated JWT value.
    override fun jwt(payload: JSONObject): String? {
        val header = JSONObject()
                .put("alg", "HS256")
                .put("typ", "JWT")

        val headerEncoded = Base64.encodeToString(header.toString().toByteArray(), Base64.DEFAULT)

        val payloadEncoded = Base64.encodeToString(payload.toString().toByteArray(), Base64.DEFAULT)

        val data = "$headerEncoded.$payloadEncoded".replace("\n","")

        val sha256_HMAC = Mac.getInstance("HmacSHA256")
        val secret_key = SecretKeySpec(constants.jwtSecretKey.toByteArray(), "HmacSHA256")
        sha256_HMAC.init(secret_key)

        val signature = Base64.encodeToString(sha256_HMAC.doFinal(data.toByteArray()), Base64.DEFAULT)
                .replace("/", "_")
                .replace("+", "-")
                .replace("=", "")

        return "$data.$signature".replace("\n","")
    }

    override fun createKey(alias: String) {
        try {
            // Create new key if needed
            if (!keyStore.containsAlias(alias)) {
                val spec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    KeyGenParameterSpec.Builder(alias,
                            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                            .build()
                } else if (context != null) {
                    val start = Calendar.getInstance()
                    val end = Calendar.getInstance()
                    end.add(Calendar.YEAR, 1)
                    KeyPairGeneratorSpec.Builder(context)
                            .setAlias(alias)
                            .setSubject(X500Principal("CN=User, O=Example Inc"))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(start.getTime())
                            .setEndDate(end.getTime())
                            .build()
                } else {
                    null
                }

                val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")
                generator.initialize(spec)

                generator.generateKeyPair()
            } else {
                deleteKey(alias)
                createKey(alias)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun deleteKey(alias: String) {
        try {
            keyStore.deleteEntry(alias)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }
    }

    override fun encrypt(value: String?): String? {
        if (value == null) {
            return null
        }

        if (!keyStore.containsAlias(ALIAS)) {
            createKey(ALIAS)
        }

        try {
            val privateKeyEntry = keyStore.getEntry(ALIAS, null) as KeyStore.PrivateKeyEntry
            val publicKey = privateKeyEntry.certificate.publicKey

            // Encrypt the text
            if (value.isEmpty()) {
                return null
            }

            val input = Cipher.getInstance(TRANSFORMATION)
            input.init(Cipher.ENCRYPT_MODE, publicKey)

            val outputStream = ByteArrayOutputStream()
            val cipherOutputStream = CipherOutputStream(
                    outputStream, input)

            cipherOutputStream.use { s ->
                s.write(value.toByteArray(charset("UTF-8")))
            }

            return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null

    }

    override fun decrypt(value: String?): String? {
        if (value == null) {
            return null
        }

        if (!keyStore.containsAlias(ALIAS)) {
            createKey(ALIAS)
        }

        try {
            val privateKeyEntry = keyStore.getEntry(ALIAS, null) as KeyStore.PrivateKeyEntry
            val privateKey = privateKeyEntry.privateKey

            val output = Cipher.getInstance(TRANSFORMATION)
            output.init(Cipher.DECRYPT_MODE, privateKey)

            val cipherInputStream = CipherInputStream(
                    ByteArrayInputStream(Base64.decode(value, Base64.DEFAULT)), output)

            var decryptedString: String? = null
            cipherInputStream.use { r ->
                r.reader().forEachLine {
                    decryptedString = it
                }
            }

            return decryptedString
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}