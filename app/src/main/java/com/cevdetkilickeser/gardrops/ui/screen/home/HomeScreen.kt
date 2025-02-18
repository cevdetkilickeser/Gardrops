package com.cevdetkilickeser.gardrops.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.R
import com.cevdetkilickeser.gardrops.ui.screen.home.component.LatestContent
import com.cevdetkilickeser.gardrops.ui.screen.home.component.PremiumContent
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import com.cevdetkilickeser.gardrops.ui.theme.SearchBarBackground

@Composable
fun HomeScreen() {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("En Yeniler", "Premium")
    val density = LocalDensity.current
    val listState = rememberLazyListState()
    var previousScrollOffset by remember { mutableIntStateOf(0) }
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                isVisible = currentOffset <= previousScrollOffset
                previousScrollOffset = currentOffset
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.png_gardrops),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag, contentDescription = null,

                        )
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically { with(density) { -40.dp.roundToPx() } } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically { with(density) { -40.dp.roundToPx() } } + shrinkVertically() + fadeOut()
        ) {
            Column {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text = "Ara",
                            color = Color.LightGray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = SearchBarBackground,
                        unfocusedContainerColor = SearchBarBackground,
                        focusedLeadingIconColor = Color.LightGray,
                        unfocusedLeadingIconColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.Transparent
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(text = title) },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = Color.LightGray
                        )
                    }
                }
            }
        }

        when (selectedTabIndex) {
            0 -> LatestContent(listState = listState) {}
            1 -> PremiumContent(listState = listState) {}
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    GardropsTheme {
        HomeScreen()
    }
}

