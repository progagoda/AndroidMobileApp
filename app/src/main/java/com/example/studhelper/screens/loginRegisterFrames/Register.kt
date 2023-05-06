package com.example.studhelper.screens.loginRegisterFrames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.Input
import com.example.studhelper.funtions.LoginAction
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun Register(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {
    var isu = rememberSaveable { mutableStateOf("") }
    var name = rememberSaveable { mutableStateOf("") }
    var surname = rememberSaveable { mutableStateOf("") }
    var password = rememberSaveable { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF262A34), Color(0xFF26264C)
                        )
                    )
                )
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Input(name = "ISU", text = isu.value, onTextChange = { isu.value = it })
            Input(name = "Name", text = name.value, onTextChange = { name.value = it })
            Input(name = "Surname", text = surname.value, onTextChange = { surname.value = it })
            Input(name = "Password", text = password.value, onTextChange = { password.value = it })
            CustomButton(
                action = {
                    LoginAction().register(
                        isu,
                        name,
                        surname,
                        password,
                        scaffoldState,
                        coroutineScope,
                        navController,
                        profileViewModel,
                    )
                }, name = "Register"
            )
        }
    }
}