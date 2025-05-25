package dev.forcetower.instalytics.android.ui.navigation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.forcetower.instalytics.android.R
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import dev.forcetower.kmm.toolkit.logdog.logdog
import kotlinx.serialization.Serializable

val rawPosts = listOf(
    "https://images.unsplash.com/photo-1747582300720-9c71ee7f7fc2?q=80&w=3496&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748096728390-0c0f12834975?q=80&w=3987&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1746980372130-27b32e22b5ad?q=80&w=3333&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747077138172-590c023ec442?q=80&w=3986&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748100377329-429f657842de?q=80&w=3867&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)

@Serializable
object ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    paddingValues: PaddingValues = PaddingValues()
) {
    val layoutDirection = LocalLayoutDirection.current
    val paddings = PaddingValues(
        start = paddingValues.calculateStartPadding(layoutDirection) + 1.dp,
        end = paddingValues.calculateEndPadding(layoutDirection) + 1.dp,
        bottom = paddingValues.calculateBottomPadding() + 1.dp,
        top = 1.dp,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = stringResource(R.string.home_profile),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        )


        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddings,
            columns = GridCells.Fixed(2)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column {
                    ProfileHeader()
                    ProfileStats()
                    ProfileContentTypes()
                    HorizontalDivider()
                }
            }
            items(rawPosts) { post ->
                ProfilePost(post) {
                    logdog { "Clicked a post! $post" }
                }
            }
        }
    }
}

@Composable
fun ProfilePost(
    post: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    AsyncImage(
        model = post,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .padding(1.dp)
            .clip(MaterialTheme.shapes.large)
            .clickable(role = Role.Button, onClick = onClick),
    )
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
    ) {
        Image(
            painter = painterResource(R.drawable.delete_img_mock_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp)
                .clip(CircleShape)
                .width(128.dp)
                .aspectRatio(1f)
        )
        Text(
            "Sophia Carter",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(top = 16.dp)
        )
        Text(
            "Digital Creator",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Text(
            "Travel enthusiast | Sharing my adventures",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.elevatedButtonColors()
            ) {
                Text(stringResource(R.string.profile_open_profile))
            }

            Spacer(Modifier.padding(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(stringResource(R.string.profile_share_profile))
            }
        }
    }
}

@Composable
fun ProfileStats() {
    val horizontalInsets = WindowInsets.safeDrawing
        .only(WindowInsetsSides.Horizontal)
        .asPaddingValues()

    val layoutDirection = LocalLayoutDirection.current

    val combined = PaddingValues(
        start = horizontalInsets.calculateStartPadding(layoutDirection) + 16.dp,
        end = horizontalInsets.calculateEndPadding(layoutDirection) + 16.dp,
    )

    LazyRow(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        contentPadding = combined,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { SmallProfileStat(title = "1.2M", description = "Posts") }
        item { SmallProfileStat(title = "8.5k", description = "Followers") }
        item { SmallProfileStat(title = "1.2k", description = "Following") }
    }
}

@Composable
fun ProfileContentTypes() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .padding(bottom = 4.dp)
            .width(IntrinsicSize.Max)
    ) {
        Icon(
            painter = painterResource(R.drawable.round_window_24),
            contentDescription = null
        )
        Text(
            stringResource(R.string.profile_content_type_posts),
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
        )
        Box(
            modifier = Modifier
                .padding(4.dp)
                .height(2.dp)
                .width(28.dp)
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
        )
    }
}

@Composable
fun SmallProfileStat(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {
        Column {
            Text(
                title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                description,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
internal fun FacebookLoginScreenPreview() {
    InstalyticsTheme {
        Scaffold { _ ->
            Profile()
        }
    }
}