package com.example.studhelper.screens.mainFrames.student

import com.example.studhelper.R
// It contains route names to all three screens
sealed class NavigationBar(var route: String, var icon: Int, var title: String) {
    object Queue : NavigationBar("queue", R.drawable.queue,"Очереди")
    object Profile: NavigationBar("profile",  R.drawable.profile, "Профиль")
    object MyGroup: NavigationBar("myGroup",  R.drawable.my_group, "Моя группа")
}
