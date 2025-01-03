package com.cevdetkilickeser.gardrops.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cevdetkilickeser.gardrops.data.model.HomeGridItem.Product
import com.cevdetkilickeser.gardrops.ui.theme.FreeShippingBackground
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import com.cevdetkilickeser.gardrops.ui.theme.NewProductBackground

@Composable
fun ProductItem(item: Product, modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth - 32.dp) / 2
    val itemHeight = itemWidth * 1.75f
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .width(itemWidth)
            .height(itemHeight)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(4.dp)
                    .size(24.dp)
            ) {
                AsyncImage(
                    model = item.sellerImage,
                    contentDescription = null
                )
            }
            Text(text = item.sellerName, fontSize = 14.sp)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            Box {
                AsyncImage(
                    model = item.productImage,
                    contentDescription = null,
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(color = Color.Transparent)
                ) {
                    Text(
                        text = "Kargo Bedava",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        modifier = Modifier
                            .weight(1f)
                            .background(color = FreeShippingBackground)
                    )
                    if (item.productCondition) {
                        Text(
                            text = "Yeni & Etiketli",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            modifier = Modifier
                                .weight(1f)
                                .background(color = NewProductBackground)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Text(
                text = "${item.productPrice} ₺",
                fontSize = 16.sp, lineHeight = 16.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.TopStart)
            )
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = item.productBrand,
                    fontSize = 12.sp, lineHeight = 12.sp
                )
                item.productSize?.let {
                    Text(
                        text = "/",
                        fontSize = 12.sp, lineHeight = 12.sp
                    )
                    Text(
                        text = it,
                        fontSize = 12.sp, lineHeight = 12.sp
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.TopEnd)
            ) {
                IconButton(onClick = {}, modifier = Modifier.size(16.dp)) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
                }
                if (item.productFavCount > 0) {
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = item.productFavCount.toString(),
                        fontSize = 14.sp, lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    GardropsTheme {
        ProductItem(
            item = Product(
                id = 1,
                sellerName = "azra01azra",
                sellerImage = "https://images.gardrops.com/uploads/10871394/avatar_photo/10871394-avatar-mid.jpg",
                productImage = "https://images.gardrops.com/uploads/10871394/user_items/108713943-s1-1730399877180-6723ce8637bd2.jpeg",
                productName = "Vintage Deri Ceket",
                productPrice = 500f,
                productBrand = "Derimod",
                productSize = "XL", productFavCount = 1,
                productCondition = true
            )
        )
    }
}