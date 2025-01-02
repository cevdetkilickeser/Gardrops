package com.cevdetkilickeser.gardrops.ui.screen.home

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cevdetkilickeser.gardrops.R
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import com.cevdetkilickeser.gardrops.ui.theme.SearchBarBackground

@Composable
fun HomeScreen() {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("En Yeniler", "Premium")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp)
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
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag, contentDescription = null,

                        )
                }
            }
        }
        OutlinedTextField(
            value = "",
            onValueChange = {},

            placeholder = {
                Text(
                    text = "Ara",
                    color = Color.LightGray,
                    lineHeight = 8.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = SearchBarBackground,
                unfocusedContainerColor = SearchBarBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(4.dp),
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
        when (selectedTabIndex) {
            0 -> LatestContent {}
            1 -> PremiumContent()
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

