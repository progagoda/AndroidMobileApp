package com.example.studhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.studhelper.screens.ChooseGroup
import com.example.studhelper.screens.Login
import com.example.studhelper.screens.Register


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                ScreenMain()
            }
        }
    }
}

@Composable
fun ScreenMain() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Login.route) {
        // First route : Login
        composable(Routes.Login.route) {
            Login(navController = navController)
        }
        // Another Route : Register
        composable(Routes.Register.route) {
            // Register Screen
            Register(navController = navController)
        }
        // Another Route : Choose group
        composable(Routes.ChooseGroup.route) {
            // Register Screen
            ChooseGroup(navController = navController)
        }

    }
}