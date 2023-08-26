package com.sierra.common.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sierra.common.ui.R

private val MainFont = FontFamily(
    Font(R.font.komikax, FontWeight.Normal),
)

fun Typography(
    primary: Color,
    secondary: Color,
) = Typography(
    h1 = TextStyle(
        fontFamily = MainFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = secondary,
    ),
    h2 = TextStyle(
        fontFamily = MainFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = secondary,
    ),
    body1 = TextStyle(
        fontFamily = MainFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = secondary,
    ),
    body2 = TextStyle(
        fontFamily = MainFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = primary,
    )
)
