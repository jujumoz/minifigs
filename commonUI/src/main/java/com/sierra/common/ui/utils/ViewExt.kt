package com.sierra.common.ui.utils

import androidx.activity.ComponentActivity
import com.google.android.material.snackbar.Snackbar


fun ComponentActivity.showError(
    text: String,
    duration: ErrorDuration = ErrorDuration.Indefinite,
) {
    Snackbar.make(
        findViewById(android.R.id.content),
        text,
        when (duration) {
            ErrorDuration.Short -> Snackbar.LENGTH_SHORT
            ErrorDuration.Long -> Snackbar.LENGTH_LONG
            ErrorDuration.Indefinite -> Snackbar.LENGTH_INDEFINITE
        },
    ).show()
}

sealed interface ErrorDuration {
    object Short : ErrorDuration
    object Long : ErrorDuration
    object Indefinite : ErrorDuration
}
