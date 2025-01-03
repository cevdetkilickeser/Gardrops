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
                val startDestination = Screen.EntryPoint
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination?.route
                Scaffold(
                    bottomBar = {
                        if (currentDestination != null) {
                            if (currentDestination.contains("Screen.Home")) {
                                BottomAppBar(
                                    currentDestination = Screen.Home,
                                    onHomeClick = { navController.navigate(Screen.Home) }
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