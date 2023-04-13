package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun createGroup(
    profileViewModel: ProfileViewModel,
    groupViewModel: GroupViewModel,
    groupNumber: MutableState<String>,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    if (groupNumber.value == "") {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Поле должно быть заполнено"
            )
        }
    } else {
        groupViewModel.groups.forEach { item ->
            if (item.name == groupNumber.value) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Данная группа уже существует, попросите у старосты код"
                    )
                }
            }
        }
        groupViewModel.addGroup(profileViewModel.currentProfile, groupNumber.value)
        profileViewModel.currentProfile.admin = true
        println(profileViewModel.currentProfile.group)
        navController.navigate(Routes.Queue.route)
    }
}

fun joinGroup(
    profileViewModel: ProfileViewModel,
    groupViewModel: GroupViewModel,
    groupCode: MutableState<String>,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    if (groupCode.value == "") {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Поле должно быть заполнено"
            )
        }
    } else {
        if (groupViewModel.setGroup(profileViewModel.currentProfile, groupCode.value)) {
            navController.navigate(Routes.Queue.route)
        } else {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Такого кода не существует"
                )
            }
        }
    }
}

fun deleteGroup(
    profileViewModel: ProfileViewModel,
    groupViewModel: GroupViewModel,
    navController: NavHostController
) {
    if (profileViewModel.currentProfile.admin) {
        groupViewModel.groups.forEach { item ->
            if (item == profileViewModel.currentProfile.group) groupViewModel.deleteGroup(item)
        }
        navController.navigate(Routes.ChooseGroup.route)
    }
}
