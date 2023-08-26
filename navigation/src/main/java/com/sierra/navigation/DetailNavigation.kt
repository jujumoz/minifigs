package com.sierra.navigation

import android.content.Context
import android.content.Intent

interface DetailNavigation {
    fun createIntent(context: Context, minifigId: String): Intent
}
