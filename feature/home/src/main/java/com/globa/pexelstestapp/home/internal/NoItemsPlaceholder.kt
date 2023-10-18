package com.globa.pexelstestapp.home.internal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.pexelstestapp.home.R
import com.globa.pexelstestapp.ui.theme.PexelsTestAppTheme

@Composable
internal fun NoItemsPlaceholder(
    modifier: Modifier = Modifier,
    onExploreButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.no_results_found),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = stringResource(id = R.string.explore),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.clickable {
                onExploreButtonClick()
            }
        )
    }
}

@Preview
@Composable
fun NoItemsPlaceholderPreview() {
    PexelsTestAppTheme {
        Surface(
            modifier = Modifier.size(400.dp)
        ) {
            NoItemsPlaceholder {

            }
        }
    }
}