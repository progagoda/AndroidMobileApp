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

fun goToQueue(subject: Subject, navController: NavController, subjectViewModel: SubjectViewModel){
    subjectViewModel.currentSubject = subject
    navController.navigate(Routes.SubjectQueue.route)
}
fun checkStateStudent(subject:Subject, profile: Profile): Boolean {
    subject.students.forEach { item->
        if(item==profile) return true
    }
    return false
}