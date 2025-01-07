package com.cevdetkilickeser.gardrops.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.cevdetkilickeser.gardrops.navigation.Screen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.composable.GradientButton
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun ProfileScreen(modifier: Modifier = Modifier,navController: NavController) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GradientButton(
            text = "SignOut",
            onClick = {
                Firebase.auth.signOut()
                navController.navigate(Screen.EntryPoint)
            }
        )
    }
}