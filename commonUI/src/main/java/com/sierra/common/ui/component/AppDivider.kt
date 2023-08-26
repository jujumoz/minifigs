package com.sierra.common.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider() {
    Divider(Modifier.padding(horizontal = 8.dp), thickness = 1.dp)
}
