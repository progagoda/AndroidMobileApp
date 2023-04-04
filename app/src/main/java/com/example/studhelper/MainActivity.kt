package com.example.studhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studhelper.screens.ChooseGroup
import com.example.studhelper.screens.loginRegisterFrames.*
import com.example.studhelper.screens.mainFrames.student.MyGroup
import com.example.studhelper.screens.mainFrames.student.NavigationBar
import com.example.studhelper.screens.mainFrames.student.Profile
import com.example.studhelper.screens.mainFrames.student.queue.CreateQueue
import com.example.studhelper.screens.mainFrames.student.queue.Queue
import com.example.studhelper.screens.mainFrames.student.queue.SubjectViewModel


class MainActivity : ComponentActivity() {
    val SubjectViewModel by viewModels<SubjectViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                ScreenMain(subjectsViewModel=SubjectViewModel)
            }
        }
    }
}

@Composable
fun ScreenMain(subjectsViewModel: SubjectViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Login.route) {
        // First route : Login
        composable(Routes.Login.route) {
            Login(navController = navController)
        }
        // Another Route : Register
        composable(Routes.Register.route) {
            // Register Screen
            Register(navController = navController)
        }
        // Another Route : Choose group
        composable(Routes.ChooseGroup.route) {
            // Register Screen
            ChooseGroup(navController = navController)
        }
        // Another Route : Create group
        composable(Routes.CreateGroup.route) {
            // Register Screen
            CreateGroup(navController = navController)
        }
        // Another Route : Join group
        composable(Routes.JoinGroup.route) {
            // Register Screen
            JoinGroup(navController = navController)
        }
        // Another Route : Queue
        composable(Routes.Queue.route) {
            Queue(navController = navController, subjectsViewModel.subjects, addSubject = {subjectsViewModel.addSubject(it)},
            deleteSubject = {subjectsViewModel.deleteSubject(it)})
        }
        // Another Route : Profile
        composable(NavigationBar.Profile.route) {
            // Register Screen
            Profile(navController = navController)
        }
        // Another Route : MyGroup
        composable(NavigationBar.MyGroup.route) {
            // Register Screen
            MyGroup(navController = navController)
        }
        // Another Route : CreateQueue
        composable(Routes.CreateQueue.route) {
            // Register Screen
            CreateQueue(navController = navController, addSubject={subjectsViewModel.addSubject(it)})
        }

    }
}