package com.example.studhelper

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.studhelper.screens.Login

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

            // Lay down the Home Composable
            // and pass the navController
            Login(navController = navController)
        }

        // Another Route : Profile
        composable(Routes.Register.route) {
            // Profile Screen
            ContactsContract.Profile()
        }

    }
}