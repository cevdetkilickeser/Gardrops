package com.cevdetkilickeser.gardrops.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.R

@Composable
fun BottomAppBar(
    currentDestination: Screen,
    onClickHome: () -> Unit,
    onClickSearch: () -> Unit,
    onClickAdd: () -> Unit,
    onClickNotifications: () -> Unit,
    onClickProfile: () -> Unit
) {
    Column {
        HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.height(70.dp)
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.png_menu_icon),
                        contentDescription = null
                    )
                },
                selected = currentDestination == Screen.Home,
                onClick = { onClickHome() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                selected = currentDestination == Screen.Search,
                onClick = { onClickSearch() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = { Icon(imageVector = Icons.Outlined.AddBox, contentDescription = null) },
                selected = currentDestination == Screen.Add,
                onClick = { onClickAdd() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = null
                    )
                },
                selected = currentDestination == Screen.Notification,
                onClick = { onClickNotifications() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                icon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = null) },
                selected = currentDestination == Screen.Profile,
                onClick = { onClickProfile() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}