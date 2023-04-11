package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.studhelper.data.Group
import com.example.studhelper.data.Profile
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun redirect(url: () -> Unit) {
    url()
}

fun register(
    isu: MutableState<String>,
    name: MutableState<String>,
    surname: MutableState<String>,
    password: MutableState<String>,
    profiles: List<Profile>,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    createProfile: (Profile) -> Unit,
    profileViewModel: ProfileViewModel,
    navController: NavController
) {
    if(isu.value.isEmpty() || name.value.isEmpty()  || surname.value.isEmpty()|| password.value.isEmpty()){
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Все поля должны быть заполнены"
            )
        }
        return
    }
    val currentProfile = (Profile(
        name = "${name.value} ${surname.value}",
        isu = isu.value,
        password = password.value,
        admin = false,
        group = Group(name="", code="")
    ))
    profiles.forEach { item ->
        if (item.isu == currentProfile.isu) {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Данное ису уже существует"
                )
            }
            return
        }
    }
    createProfile(currentProfile)
    profileViewModel.setProfile(currentProfile)
    profileViewModel.currentProfile = currentProfile
    navController.navigate(Routes.ChooseGroup.route)
}

fun checkUser(
    login: String,
    password: String,
    profileViewModel: ProfileViewModel,
    navController: NavController,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    var currentProfile: Profile? = null;
    if (login != "" && password != "") {
        profileViewModel.profiles.forEach() { profile ->
            if (profile.isu == login && profile.password == password)
                if (!profile.group.equals("")) {
                    currentProfile = profile
                } else redirect { navController.navigate(Routes.ChooseGroup.route) }
            else coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Ваши данные неверны"
                )
            }
        }
    } else {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Поля не могут быть пустыми"
            )
        }
    }
    if (currentProfile != null) {
        println(currentProfile)
        profileViewModel.setProfile(currentProfile!!);
        navController.navigate(Routes.Queue.route)
    }
}
fun logOut(profileViewModel: ProfileViewModel, navController: NavController){
    profileViewModel.currentProfile=profileViewModel.profiles[0]
    navController.navigate(Routes.Login.route)
}
// TODO сделать проверку на есть ли такой код группы, если да то присоединить профиль к этой группе


fun joinGroup(){

}