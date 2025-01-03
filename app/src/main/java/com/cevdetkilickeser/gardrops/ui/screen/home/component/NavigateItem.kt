package com.cevdetkilickeser.gardrops.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cevdetkilickeser.gardrops.data.model.HomeGridItem.NavigateItem
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun NavigateItem(item: NavigateItem, modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth - 32.dp) / 2
    val itemHeight = itemWidth * 1.75f
    Card(
        modifier = modifier
            .padding(vertical = 24.dp)
            .width(itemWidth)
            .height(itemHeight)
    ) {
        Box{
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun NavigateItemPreview() {
    GardropsTheme {
        NavigateItem(
            item = NavigateItem(
                id = 1,
                title = "Tüm  ayakkabılar 49 TL",
                image = "https://images.gardrops.com/uploads/10871394/user_items/108713943-"
            )
        )
    }
}