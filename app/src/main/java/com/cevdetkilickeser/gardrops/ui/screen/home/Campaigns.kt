package com.cevdetkilickeser.gardrops.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.data.model.campaignLists
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun Campaigns() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Kampanyalar",
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "Tümünü Gör",
                color = Color.LightGray,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(10) { index ->
                val campaign = campaignLists[index]
                CampaignItem(campaign = campaign)
            }
        }
    }
}

@Preview
@Composable
private fun CampaignsPreview() {
    GardropsTheme {
        Campaigns()
    }
}