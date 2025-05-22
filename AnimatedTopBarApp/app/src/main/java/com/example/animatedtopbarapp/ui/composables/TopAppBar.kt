package com.example.animatedtopbarapp.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme // Material 3 Theme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedTopAppBar(
    title: String,
    scrollOffset: Float
) {
    val minFontSize = 18.sp
    val maxFontSize = 32.sp
    val scrollRange = 300f // Pixels

    // Calculate the fraction of scroll, clamped between 0 and 1
    val scrollFraction = (scrollOffset / scrollRange).coerceIn(0f, 1f)

    // Interpolate font size
    // When scrollFraction is 0 (no scroll), fontSize is maxFontSize.
    // When scrollFraction is 1 (scrolled past scrollRange), fontSize is minFontSize.
    val currentFontSizeSp = lerp(maxFontSize, minFontSize, scrollFraction)

    // Animate the font size
    val animatedFontSize by animateFloatAsState(
        targetValue = currentFontSizeSp.value, // Animate the .value of the TextUnit
        label = "FontSizeAnimation"
    )

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = animatedFontSize.sp // Use the animated value and convert back to .sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
