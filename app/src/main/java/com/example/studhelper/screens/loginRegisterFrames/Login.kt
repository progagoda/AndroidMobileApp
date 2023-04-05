package com.example.studhelper.screens.loginRegisterFrames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.Input
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun Login(navController: NavHostController, profileViewModel: ProfileViewModel) {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    fun redirect(){
        navController.navigate(Routes.ChooseGroup.route)
    }
    fun redirect2(){
        navController.navigate(Routes.Register.route)
    }
    fun hello(){
        println("Hi")
    }
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF262A34),
                        Color(0xFF26264C)
                    )
                )
            )
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Input(name = "Login", text = login, onTextChange = { login = it })
        Input(name = "Password", text = password, onTextChange = { password = it })
        CustomButton(actionArr = arrayOf(::hello,::redirect), name = "Login")
        CustomButton(actionArr = arrayOf(::hello,::redirect2), name = "Register")
    }
}
