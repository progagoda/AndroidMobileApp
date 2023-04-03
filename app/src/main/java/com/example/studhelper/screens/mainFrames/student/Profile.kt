package com.example.studhelper.screens.mainFrames.student

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studhelper.InterFamily
import com.example.studhelper.components.BottomMenu

@Composable
fun Profile(navController: NavHostController) {
    val subject = arrayListOf<String>(
        "Методы и средства программной инженерии",
        "Веб-программирование",
        "Архитектура программных систем",
        "Информационные системы и базы данных",
        "Проектирование пользовательских интерфейсов",
        "Архитектура компьютера",
        "Бизнес процессы программных систем",
        "Тестирование программного обеспечения"
    )
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage= "Профиль") },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Text(
                text = "Профиль",
                fontSize = 30.sp,
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
          )},
        backgroundColor = Color(0xFF26264C) // Set background color to avoid the white flashing when you switch between screens
    )
}