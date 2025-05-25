package dev.forcetower.instalytics.android.ui.navigation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(paddingValues: PaddingValues) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.home_insight),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        )
    }
}

@Preview
@Composable
internal fun SearchPreview() {
    InstalyticsTheme {
        Scaffold { padding ->
            Search(padding)
        }
    }
}