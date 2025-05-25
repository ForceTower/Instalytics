package dev.forcetower.instalytics.android.ui.navigation.insights

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.navigation.search.Search
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Insights(padding: PaddingValues) {
    val tabTitles = listOf("Account insights", "Post insights")
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        TopAppBar(
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.home_insight),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        )
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 8.dp,
            indicator = { tabPositions ->
//                Box(
//                    Modifier
//                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
//                        .width(IntrinsicSize.Min)
//                        .height(2.dp)
//                        .background(Color.Red)
//                )
                FancyIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
//                TabRowDefaults.SecondaryIndicator(
//                    modifier = Modifier
//                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
//                        .padding(horizontal = 8.dp)
//                        .padding(top = 8.dp),
//                    height = 1.dp
//                )
            },
            divider = {}
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            title,
                            color = if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.onBackground
                            } else {
                                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                            }
                        )
                    }
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize()
                    .align(Alignment.CenterHorizontally)
            ) {
                if (index == 0) {
                    AccountInsight(padding)
                } else {
                    PostInsight(padding)
                }
            }
        }
    }
}

@Composable
fun FancyIndicator(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier
            .width(IntrinsicSize.Min)
            .height(2.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
            .background(color)
    )
}

@Preview
@Composable
internal fun InsightsPreview() {
    InstalyticsTheme {
        Scaffold { padding ->
            Insights(padding)
        }
    }
}