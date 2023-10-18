package com.globa.pexelstestapp.home.internal

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.pexelstestapp.home.R
import com.globa.pexelstestapp.ui.theme.PexelsTestAppTheme

@Composable
fun NetworkErrorPlaceholder(
    modifier: Modifier = Modifier,
    onRefreshButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_no_network),
            contentDescription = "No network",
            modifier = Modifier.size(width = 125.dp, height = 100.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.try_again),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.clickable {
                onRefreshButtonClick()
            }
                .padding(top = 24.dp)
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NetworkErrorPlaceholderPreview() {
    PexelsTestAppTheme {
        Surface(
            modifier = Modifier.size(400.dp)
        ) {
            NetworkErrorPlaceholder {

            }
        }
    }
}