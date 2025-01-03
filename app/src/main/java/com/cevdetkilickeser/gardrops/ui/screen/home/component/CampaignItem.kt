package com.cevdetkilickeser.gardrops.ui.screen.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cevdetkilickeser.gardrops.data.model.Campaign
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun CampaignItem(campaign: Campaign) {
    Card(
        modifier = Modifier
            .width(175.dp)
            .height(100.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            AsyncImage(
                model = campaign.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = campaign.title,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CampaignItemPreview() {
    GardropsTheme {
        CampaignItem(campaign = Campaign(1, "Ev & İç Giyimde Şık Ürünler", "https://images.gardrops.com/campaign_uploads/images/2019/3/camp_img_5c8bbaeee4c70.jpg"))
    }
}