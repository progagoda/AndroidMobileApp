package com.example.studhelper.funtions

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.example.studhelper.data.Profile
import com.example.studhelper.data.Subject
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import com.example.studhelper.screens.mainFrames.student.queue.SubjectViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun createQueue(input: MutableState<String>, profileViewModel: ProfileViewModel, scaffoldState: ScaffoldState, groupViewModel: GroupViewModel,
                coroutineScope: CoroutineScope, navController: NavController, addSubject: (Subject)->Unit
){
    if (input.value == "") {
        coroutineScope.launch{
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Поле не может быть пустым")
        }
    }
    if(!profileViewModel.currentProfile.admin) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Вы не админ своей группы"
            )
        }
    }
    else {
        addSubject(Subject(name = input.value, profileViewModel.currentProfile, listOf()))
        navController.navigate(Routes.Queue.route)
    }
}

fun deleteQueue(currentProfile: Profile,scaffoldState: ScaffoldState, subject: Subject,
                coroutineScope: CoroutineScope,subjectViewModel: SubjectViewModel) {
    if (currentProfile.group == subject.admin.group && currentProfile.admin){
        subjectViewModel.deleteSubject(subject)
    }
    else{
        coroutineScope.launch{
            scaffoldState.snackbarHostState.showSnackbar(
                message = "У вас нет прав на удаление очереди")
        }
    }
}
fun goToQueue(subject: Subject, navController: NavController, subjectViewModel: SubjectViewModel){
    subjectViewModel.currentSubject=subject
    navController.navigate(Routes.SubjectQueue.route)
}
fun checkStateStudent(subject:Subject, profile: Profile): Boolean {
    subject.students.forEach { item->
        if(item==profile) return true
    }
    return false
}
fun subscribeQueue(){}
fun unSubscribeQueue(){}