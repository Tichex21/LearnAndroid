package com.sachinshah.practical.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.sachinshah.practical.R


val MontserratFont = FontFamily(
    Font(
        R.font.montserrat_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.montserrat_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.montserrat_medium,
        FontWeight.SemiBold
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily =MontserratFont,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.dancing_script)),

        ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.lato_regular)),

        )
    ,
    titleLarge = TextStyle(
        fontFamily = MontserratFont,
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratFont,

        )
    /*
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)