package de.sortingfarmer.manju.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Immutable
data class ExtendedColorScheme(
    val statusRed: ColorFamily,
    val statusGreen: ColorFamily,
    val statusYellow: ColorFamily,
    val statusBlue: ColorFamily,
    val statusGray: ColorFamily,
    val statusPurple: ColorFamily,
    val indicationBlue: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

val extendedLight = ExtendedColorScheme(
    statusRed = ColorFamily(
        statusRedLight,
        onStatusRedLight,
        statusRedContainerLight,
        onStatusRedContainerLight,
    ),
    statusGreen = ColorFamily(
        statusGreenLight,
        onStatusGreenLight,
        statusGreenContainerLight,
        onStatusGreenContainerLight,
    ),
    statusYellow = ColorFamily(
        statusYellowLight,
        onStatusYellowLight,
        statusYellowContainerLight,
        onStatusYellowContainerLight,
    ),
    statusBlue = ColorFamily(
        statusBlueLight,
        onStatusBlueLight,
        statusBlueContainerLight,
        onStatusBlueContainerLight,
    ),
    statusGray = ColorFamily(
        statusGrayLight,
        onStatusGrayLight,
        statusGrayContainerLight,
        onStatusGrayContainerLight,
    ),
    statusPurple = ColorFamily(
        statusPurpleLight,
        onStatusPurpleLight,
        statusPurpleContainerLight,
        onStatusPurpleContainerLight,
    ),
    indicationBlue = ColorFamily(
        indicationBlueLight,
        onIndicationBlueLight,
        indicationBlueContainerLight,
        onIndicationBlueContainerLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    statusRed = ColorFamily(
        statusRedDark,
        onStatusRedDark,
        statusRedContainerDark,
        onStatusRedContainerDark,
    ),
    statusGreen = ColorFamily(
        statusGreenDark,
        onStatusGreenDark,
        statusGreenContainerDark,
        onStatusGreenContainerDark,
    ),
    statusYellow = ColorFamily(
        statusYellowDark,
        onStatusYellowDark,
        statusYellowContainerDark,
        onStatusYellowContainerDark,
    ),
    statusBlue = ColorFamily(
        statusBlueDark,
        onStatusBlueDark,
        statusBlueContainerDark,
        onStatusBlueContainerDark,
    ),
    statusGray = ColorFamily(
        statusGrayDark,
        onStatusGrayDark,
        statusGrayContainerDark,
        onStatusGrayContainerDark,
    ),
    statusPurple = ColorFamily(
        statusPurpleDark,
        onStatusPurpleDark,
        statusPurpleContainerDark,
        onStatusPurpleContainerDark,
    ),
    indicationBlue = ColorFamily(
        indicationBlueDark,
        onIndicationBlueDark,
        indicationBlueContainerDark,
        onIndicationBlueContainerDark,
    ),
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

// Create a CompositionLocal to hold the ExtendedColorScheme.
private val LocalExtendedColorScheme = staticCompositionLocalOf<ExtendedColorScheme> {
    error("No ExtendedColorScheme provided")
}

@Composable
fun ManjuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkScheme
        else -> lightScheme
    }

    // Choose the proper extended color scheme based on the current theme.
    val extendedColorScheme = if (darkTheme) extendedDark else extendedLight

    // Provide the extended colors using CompositionLocalProvider.
    CompositionLocalProvider(LocalExtendedColorScheme provides extendedColorScheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}

// Create an easy-access object to retrieve the extended colors in your composables.
object ManjuThemeExtended {
    val extendedColors: ExtendedColorScheme
        @Composable
        get() = LocalExtendedColorScheme.current
}
