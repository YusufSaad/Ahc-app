package com.example.coreandroid.sources.logging

import android.util.Log
import com.example.coreandroid.sources.enums.Environment.*
import com.example.coreandroid.sources.enums.Environment
import com.example.coreandroid.sources.logging.destinations.BaseDestination

object LogHelper {

    private val LOG_PREFIX = "app_core"
    private val LOG_PREFIX_LENGTH = LOG_PREFIX.length
    private val MAX_LOG_TAG_LENGTH = 23

    val destinations: ArrayList<BaseDestination> = arrayListOf()

    fun makeLogTag(str: String): String {
        return if (str.length > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1)
        } else LOG_PREFIX + str
    }

    /**
     * Don't use this when obfuscating class names!
     */
    fun makeLogTag(cls: Class<*>): String {
        return makeLogTag(cls.simpleName)
    }


    fun v(tag: String = makeLogTag(""), vararg messages: Any) {
        // Only log VERBOSE if build type is DEBUG
        if (Environment.mode == DEVELOPMENT) {
            log(tag, Log.VERBOSE, null, *messages)
        }
    }

    fun d(tag: String = makeLogTag(""), vararg messages: Any) {
        // Only log DEBUG if build type is DEBUG
        if (Environment.mode == DEVELOPMENT) {
            log(tag, Log.DEBUG, null, *messages)
        }
    }

    fun i(tag: String = makeLogTag(""), vararg messages: Any) {
        log(tag, Log.INFO, null, *messages)
    }

    fun w(tag: String = makeLogTag(""), vararg messages: Any) {
        log(tag, Log.WARN, null, *messages)
    }

    fun w(tag: String = makeLogTag(""), t: Throwable, vararg messages: Any) {
        log(tag, Log.WARN, t, *messages)
    }

    fun e(tag: String = makeLogTag(""), vararg messages: Any) {
        log(tag, Log.ERROR, null, *messages)
    }

    fun e(tag: String = makeLogTag(""), t: Throwable, vararg messages: Any) {
        log(tag, Log.ERROR, t, *messages)
    }

    private fun log(tag: String, level: Int, t: Throwable?, vararg messages: Any) {
        if (Log.isLoggable(tag, level)) {
            val message: String
            message = if (t == null && messages.size == 1) {
                // handle this common case without the extra cost of creating a stringbuffer:
                messages[0].toString()
            } else {
                val sb = StringBuilder()
                for (m in messages) {
                    sb.append(m)
                }
                if (t != null) {
                    sb.append("\n").append(Log.getStackTraceString(t))
                }
                sb.toString()
            }

            // Use stackTrace 5 as defined by the depth of the call
            val stackTrace = Thread.currentThread().stackTrace[5]

            destinations.forEach {
                it.send(level = level, msg = message, file = stackTrace.fileName,
                        function = stackTrace.methodName, line = stackTrace.lineNumber,
                        context = Logger.metaLog)
            }

            longLog(level, tag, message)
        }
    }

    private fun longLog(level: Int, tag: String, message: String) {
        if (message.length > 4000) {
            Log.println(level, tag, message.substring(0, 4000))
            longLog(level, tag, message.substring(4000))
        } else
            Log.println(level, tag, message)
    }
}
