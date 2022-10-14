package com.entropylabs.jiittimetable.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.entropylabs.jiittimetable.R
val GilroyRegular = FontFamily(Font(R.font.gilroyregular))
val GilroyMedium = FontFamily(Font(R.font.gilroymedium))
val GilroyBold = FontFamily(Font(R.font.gilroybold))
val GilroyExtraBold = FontFamily(Font(R.font.gilroyextrabold))
//heavy
val GilroyHeavy = FontFamily(Font(R.font.gilroyheavy))
//light
val GilroyLight = FontFamily(Font(R.font.gilroylight))
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = GilroyRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

