package com.example.studhelper


// It contains route names to all three screens
sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register: Routes("register")
}