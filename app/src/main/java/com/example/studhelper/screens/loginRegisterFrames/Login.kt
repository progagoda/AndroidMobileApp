package com.example.studhelper.screens.loginRegisterFrames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.Input

@Composable
fun Login(navController: NavHostController){
    Column(
        modifier = Modifier
            .background(brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF262A34),
                    Color(0xFF26264C)
                )
            ))
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Input(name = "Login")
        Input(name = "Password")
        CustomButton(action = {navController.navigate(Routes.ChooseGroup.route)}, name ="Login" )
        CustomButton(action = {navController.navigate(Routes.Register.route)}, name ="Register" )
    }
}