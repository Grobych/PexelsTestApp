package com.globa.pexelstestapp.home.internal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.pexelstestapp.ui.theme.PexelsTestAppTheme

@Composable
fun ScrollAnimation(
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        modifier = modifier
            .height(4.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surface,
        strokeCap = StrokeCap.Round
    )
}

@Preview
@Composable
fun ScrollAnimationPreview() {
    PexelsTestAppTheme {
        Row(
            modifier = Modifier.size(width = 360.dp, height = 50.dp)
        ) {
            ScrollAnimation()
        }
    }
}