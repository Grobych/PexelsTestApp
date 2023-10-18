package com.globa.pexelstestapp.home.internal

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globa.pexelstestapp.home.R
import com.globa.pexelstestapp.ui.theme.PexelsTestAppTheme
import com.globa.pexelstestapp.ui.theme.searchFieldHeight

@Composable
internal fun SearchField(
    modifier: Modifier = Modifier,
    text: String,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onTextFieldChanged(it) },
        modifier = modifier
            .height(searchFieldHeight)
            .background(color = MaterialTheme.colorScheme.background),
        singleLine = true,
        shape = MaterialTheme.shapes.extraLarge,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        placeholder = {
            Text(text = "Search", color = MaterialTheme.colorScheme.secondary)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchFieldPreview() {
    PexelsTestAppTheme {
        Row(
            modifier = Modifier.size(width = 360.dp, height = 100.dp)
        ) {
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 26.dp),
                text = "",
                onTextFieldChanged = {}
            )
        }
    }
}