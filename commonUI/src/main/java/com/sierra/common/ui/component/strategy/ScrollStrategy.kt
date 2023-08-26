package com.sierra.common.ui.component.strategy

import androidx.compose.foundation.gestures.ScrollableState

interface ScrollStrategy {
    fun getScrollOffset(): Float
    fun getScrollState(): ScrollableState
}
