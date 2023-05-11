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
import com.example.studhelper.funtions.GroupAction
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

//TODO сдедать реализацию присоединения к группе в виде соотв функций
@Composable
fun JoinGroup(navController: NavHostController,profileViewModel: ProfileViewModel){
    var groupCode = rememberSaveable { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState
    ) {
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
            Input(
                name = "Group code",
                text = groupCode.value,
                onTextChange = { groupCode.value = it })
            CustomButton(action = {
                GroupAction(profileViewModel).joinGroup(profileViewModel,groupCode,navController,scaffoldState,coroutineScope)
            }, name = "Join")
        }
    }
}