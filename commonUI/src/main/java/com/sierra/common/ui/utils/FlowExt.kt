package com.sierra.common.ui.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> ComponentActivity.launchAndCollectWhileStarted(flow: Flow<T>, block: suspend (T) -> Unit) {
    (this as LifecycleOwner).launchAndCollectWhileStarted(flow, block)
}

fun <T> LifecycleOwner.launchAndCollectWhileStarted(flow: Flow<T>, block: suspend (T) -> Unit) {
    lifecycleScope.launch {
        lifecycle.whenStarted {
            flow.collect(block)
        }
    }
}
