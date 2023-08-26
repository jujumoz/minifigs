package com.sierra.common.ui.utils

import android.content.Intent
import android.os.BadParcelableException
import android.os.Build
import android.util.Log
import java.io.Serializable

inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(name: String): T? {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializableExtra(name, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            getSerializableExtra(name) as T?
        }
    } catch (e: BadParcelableException) {
        Log.w("$e", e)
        null
    }
}
