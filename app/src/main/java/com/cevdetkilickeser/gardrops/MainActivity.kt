package com.cevdetkilickeser.gardrops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cevdetkilickeser.gardrops.navigation.AppNavigation
import com.cevdetkilickeser.gardrops.navigation.BottomAppBar
import com.cevdetkilickeser.gardrops.navigation.Screen
import com.cevdetkilickeser.gardrops.navigation.bottomBarScreens
import com.cevdetkilickeser.gardrops.navigation.toScreen
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GardropsTheme {
                val navController = rememberNavController()
                val startDestination = Screen.Home
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination?.route
                Scaffold(
                    bottomBar = {
                        currentDestination?.let {
                            if (currentDestination.toScreen() in bottomBarScreens) {
                                BottomAppBar(
                                    currentDestination.toScreen(),
                                    onClickHome = { navController.navigate(Screen.Home) },
                                    onClickSearch = { navController.navigate(Screen.Search) },
                                    onClickAdd = { navController.navigate(Screen.Add) },
                                    onClickNotifications = { navController.navigate(Screen.Notification) },
                                    onClickProfile = { navController.navigate(Screen.Profile) }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    AppNavigation(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}