package com.example.studhelper.screens.loginRegisterFrames

import com.example.studhelper.screens.mainFrames.student.NavigationBar


// It contains route names to all three screens
sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register: Routes("register")
    object ChooseGroup: Routes("chooseGroup")
    object CreateGroup: Routes("createGroup")
    object JoinGroup: Routes("joinGroup")
    object Queue: Routes("Queue")
}