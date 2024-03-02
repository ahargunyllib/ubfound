package com.ahargunyllib.internraion.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.features.presentation.screen.auth.login.LoginScreen
import com.ahargunyllib.internraion.features.presentation.screen.auth.signup.RegisterScreen
import com.ahargunyllib.internraion.features.presentation.screen.home.HomeScreen
import com.ahargunyllib.internraion.features.presentation.screen.maps.MapsScreen
import com.ahargunyllib.internraion.features.presentation.screen.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }

        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("login"){
            LoginScreen(navController = navController)
        }

        composable("maps"){
            MapsScreen(navController = navController)
        }

        composable("home"){
            HomeScreen(navController = navController)
        }
    }
}