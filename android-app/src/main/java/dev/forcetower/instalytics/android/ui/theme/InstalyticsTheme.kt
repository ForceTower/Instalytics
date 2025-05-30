package dev.forcetower.instalytics.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import dev.forcetower.instalytics.android.R

@Composable
fun InstalyticsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val vietnam = GoogleFont("Be Vietnam Pro")
    val vietnamFont = FontFamily(
        Font(googleFont = vietnam, fontProvider = provider)
    )

    val sfFont = FontFamily(
        Font(R.font.sf_pro_display_regular, FontWeight.Normal),
        Font(R.font.sf_pro_display_medium, FontWeight.Medium),
        Font(R.font.sf_pro_display_bold, FontWeight.Bold),
        Font(R.font.sf_pro_display_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
        Font(R.font.sf_pro_display_thin_italic, FontWeight.Thin, FontStyle.Italic),
        Font(R.font.sf_pro_display_ultra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
        Font(R.font.sf_pro_display_heavy_italic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.sf_pro_display_black_italic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.sf_pro_display_light_italic, FontWeight.Light, FontStyle.Italic),
    )

    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3)
        )
    }
    val defaultTypography = Typography()
    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = vietnamFont),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = vietnamFont),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = vietnamFont),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = vietnamFont),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = vietnamFont),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = vietnamFont),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = vietnamFont),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = vietnamFont),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = vietnamFont),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = vietnamFont),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = vietnamFont),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = vietnamFont),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = vietnamFont),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = vietnamFont),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = vietnamFont),
    )
    val shapes = Shapes(
        extraSmall = RoundedCornerShape(12.dp),
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
