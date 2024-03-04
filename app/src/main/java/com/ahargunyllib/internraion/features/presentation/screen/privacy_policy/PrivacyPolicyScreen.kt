package com.ahargunyllib.internraion.features.presentation.screen.privacy_policy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ahargunyllib.internraion.ui.theme.InternraionTheme

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    InternraionTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
            Text(text = "Privacy and Policy")
        }
    }
}