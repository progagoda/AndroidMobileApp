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
import com.example.studhelper.data.Profile
import com.example.studhelper.screens.loginRegisterFrames.ChooseGroup
import com.example.studhelper.screens.loginRegisterFrames.*
import com.example.studhelper.screens.mainFrames.student.myGroup.MyGroup
import com.example.studhelper.screens.mainFrames.student.NavigationBar
import com.example.studhelper.screens.mainFrames.student.Profile
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import com.example.studhelper.screens.mainFrames.student.queue.CreateQueue
import com.example.studhelper.screens.mainFrames.student.queue.Queue
import com.example.studhelper.screens.mainFrames.student.queue.SubjectQueue
import com.example.studhelper.screens.mainFrames.student.queue.SubjectViewModel

//TODO cделать фрейм записи и отписи от каждого очереди
class MainActivity : ComponentActivity() {
    private val SubjectViewModel by viewModels<SubjectViewModel>()
    private val ProfileViewModel by viewModels<ProfileViewModel>()
    private  val GroupViewModel by viewModels<GroupViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                ScreenMain(subjectsViewModel=SubjectViewModel, profileViewModel = ProfileViewModel, groupViewModel = GroupViewModel)
            }
        }
    }
}

@Composable
fun ScreenMain(subjectsViewModel: SubjectViewModel, profileViewModel: ProfileViewModel, groupViewModel: GroupViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Login.route) {
        // First route : Login
        composable(Routes.Login.route) {
            Login(navController = navController, profileViewModel)
        }
        // Another Route : Register
        composable(Routes.Register.route) {
            // Register Screen
            Register(navController = navController, profiles = profileViewModel.profiles, createProfile = {profileViewModel.addProfile(it)},
            profileViewModel=profileViewModel)
        }
        // Another Route : Choose group

        composable(Routes.ChooseGroup.route) {
            // Register Screen
            ChooseGroup(navController = navController, profileViewModel = profileViewModel)
        }
        // Another Route : Join group
        composable(Routes.JoinGroup.route) {
            // Register Screen
            JoinGroup(navController = navController, profileViewModel,groupViewModel)
        }
        // Another Route : Create group
        composable(Routes.CreateGroup.route) {
            CreateGroup(navController = navController, profileViewModel = profileViewModel, groupViewModel)


        }
        // Another Route : Queue
        composable(Routes.Queue.route) {
            Queue(
                navController = navController,
                profileViewModel= profileViewModel,
                {subjectsViewModel.deleteSubject(it)}, subjectsViewModel)
        }
        // Another Route : Profile
        composable(NavigationBar.Profile.route) {
            // Register Screen
            Profile(navController = navController, profileViewModel=profileViewModel)
        }
        // Another Route : MyGroup
        composable(NavigationBar.MyGroup.route) {
            // Register Screen
            MyGroup(navController = navController, profileViewModel=profileViewModel)
        }
        // Another Route : CreateQueue
        composable(Routes.CreateQueue.route) {
            // Register Screen
            CreateQueue(navController = navController, addSubject={subjectsViewModel.addSubject(it)}, profileViewModel = profileViewModel, groupViewModel = groupViewModel)
        }
        composable(Routes.SubjectQueue.route){
            SubjectQueue(navController = navController, subjectViewModel = subjectsViewModel, profileViewModel = profileViewModel)
        }

    }
}