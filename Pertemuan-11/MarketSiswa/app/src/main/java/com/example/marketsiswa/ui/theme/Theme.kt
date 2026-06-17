// app/src/main/java/com/example/marketsiswa/ui/theme/Theme.kt
package com.example.marketsiswa.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryPurple = Color(0xFF5C5FEF)
val OnPrimary = Color(0xFFFFFFFF)
val Surface = Color(0xFFF8F9FC)
val OnSurface = Color(0xFF1A1A2E)
val CardBackground = Color(0xFFFFFFFF)
val SubtleGray = Color(0xFF9E9E9E)
val CategoryChipColor = Color(0xFFEEF0FF)
val CategoryChipText = Color(0xFF5C5FEF)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryPurple,
    onPrimary = OnPrimary,
    background = Surface,
    surface = CardBackground,
    onSurface = OnSurface,
    secondary = Color(0xFF03DAC6),
    surfaceVariant = Color(0xFFF0F1FF)
)

@Composable
fun MarketplaceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}