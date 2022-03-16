package com.example.spacexlaunch.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.spacexlaunch.R

// Set of Material typography styles to start with
val vt323 = FontFamily(
    Font(R.font.vt323_regular)
)

val tech = FontFamily(
    Font(R.font.sharetech_regular)
)

val custTypography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = vt323,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = tech,
        fontWeight = FontWeight.Normal
)

)

