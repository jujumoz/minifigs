package com.sierra.common.di

import kotlin.coroutines.CoroutineContext

interface DBCoroutineDispatcher {
    fun get(): CoroutineContext
}
