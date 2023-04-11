package com.example.studhelper.screens.loginRegisterFrames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.Input
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun CreateGroup(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    groupViewModel: GroupViewModel
) {
    val groupNumber = rememberSaveable { mutableStateOf("") }
    fun joinGroup() {
        val profileObject = profileViewModel.currentProfile
        groupViewModel.addGroup(profileObject, groupNumber.value)
    }

    fun redirect() {
        navController.navigate(Routes.Queue.route)
    }
    Column(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
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
            name = "Group number",
            text = groupNumber.value,
            onTextChange = { groupNumber.value = it })
        CustomButton(actionArr = arrayOf(::joinGroup, ::redirect), name = "Create")
    }
}