package dev.forcetower.instalytics.android.ui.navigation.insights

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostInsight(paddingValues: PaddingValues) {
    var showSheet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            "Post insights",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(listOf(1, 2, 3)) {
                Box(
                    Modifier
                        .padding(4.dp)
                        .width(150.dp)
                        .height(150.dp)
                        .background(Color.Red)
                        .clickable {
                            showSheet = true
                        }
                )
            }
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        Modifier
                            .padding(4.dp)
                            .width(150.dp)
                            .height(150.dp)
                            .background(Color.Red)
                    )
                    Text(
                        "Modal bottom sheet",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AccountInsightPreview() {
    InstalyticsTheme {
        Scaffold { padding ->
            PostInsight(padding)
        }
    }
}