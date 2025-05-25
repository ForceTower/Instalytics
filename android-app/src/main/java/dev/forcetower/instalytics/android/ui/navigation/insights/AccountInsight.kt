package dev.forcetower.instalytics.android.ui.navigation.insights

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.extensions.format
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.IndicatorCount
import ir.ehsannarmani.compose_charts.models.IndicatorPosition
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.LineProperties
import ir.ehsannarmani.compose_charts.models.PopupProperties

@Composable
fun AccountInsight(
    paddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()
    val primaryColor = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Text(
            "Overview",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .height(IntrinsicSize.Min)
        ) {
            AccountInsightBox("Followers", "1.2k", 10, modifier = Modifier.weight(1f))
            Spacer(Modifier.size(8.dp))
            AccountInsightBox("Accounts Reached", "1.1k", 12, modifier = Modifier.weight(1f))
        }

        Row(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .height(IntrinsicSize.Min)
        ) {
            AccountInsightBox("Content Interactions", "1.2k", 15, modifier = Modifier.weight(1f))
            Spacer(Modifier.size(8.dp))
            AccountInsightBox("Total Impressions", "11k", -2, modifier = Modifier.weight(1f))
        }


        Text(
            "Followers",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Light),
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Text(
            "1.2k",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 4.dp)
        )

        Row(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .padding(horizontal = 16.dp)
                .padding(top = 2.dp)
        ) {
            Text(
                "Last 30 days",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
            Text(
                "+10%",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF088738),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
        }

        LineChart(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .height(200.dp),
            data = remember {
                listOf(
                    Line(
                        label = "Followers",
                        values = listOf(842.0, 750.0, 1248.0, 1004.0, 1211.0),
                        color = SolidColor(primaryColor),
                        firstGradientFillColor = primaryColor.copy(alpha = .5f),
                        secondGradientFillColor = Color.Transparent,
                        strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                        gradientAnimationDelay = 1000,
                        drawStyle = DrawStyle.Stroke(width = 2.dp),
                    )
                )
            },
            labelHelperProperties = LabelHelperProperties(enabled = false), // data identifier (Followers)
            gridProperties = GridProperties(
                enabled = true,
                yAxisProperties = GridProperties.AxisProperties(
                    enabled = false
                ),
                xAxisProperties = GridProperties.AxisProperties(
                    enabled = true,
                    lineCount = 5
                )
            ),
            labelProperties = LabelProperties(
                enabled = false,
                labels = listOf("What", "Is", "Going", "On", "here", "wht", "1", "2"),
                textStyle = TextStyle.Default.copy(color = Color.Red)
            ),
            popupProperties= PopupProperties(
                textStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentBuilder = { _, _, value -> value.format(0) }
            ),
            dividerProperties = DividerProperties(
                xAxisProperties = LineProperties(
                    enabled = false
                ),
                yAxisProperties = LineProperties(
                    enabled = false
                )
            ),
            indicatorProperties = HorizontalIndicatorProperties(
                enabled = true,
                textStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                contentBuilder = { value -> value.format(0) },
                padding = 8.dp,
                position = IndicatorPosition.Horizontal.Start,
                count = IndicatorCount.CountBased(5)
            ),
            animationMode = AnimationMode.Together(delayBuilder = { it * 500L }),
            minValue = 0.0,
            maxValue = 1400.0
        )

        Spacer(Modifier.padding(paddingValues))
    }
}

@Composable
private fun AccountInsightBox(
    title: String,
    metric: String,
    percentage: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxHeight()
            .padding(24.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Thin)
        )
        Text(
            metric,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black)
        )
        Text(
            "${if (percentage > 0) "+" else ""}$percentage%",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Thin),
            color = if (percentage > 0) Color(0xFF088738) else MaterialTheme.colorScheme.error
        )
    }
}

@Preview
@Composable
private fun AccountInsightPreview() {
    InstalyticsTheme {
        Scaffold { padding ->
            AccountInsight(padding)
        }
    }
}