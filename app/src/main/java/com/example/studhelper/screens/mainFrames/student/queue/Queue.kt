package com.example.studhelper.screens.mainFrames.student.queue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studhelper.InterFamily
import com.example.studhelper.components.BottomMenu
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.SubjectCard
import com.example.studhelper.data.Subject
import com.example.studhelper.funtions.QueueAction
import com.example.studhelper.funtions.loadQueue
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun Queue(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    deleteSubject: (Subject) -> Unit,
    subjectViewModel: SubjectViewModel
) {
    val admin =
        profileViewModel.currentProfile.admin;// depends on user usual student or admin of group
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val subjects = QueueAction(profileViewModel).getAllQueues(profileViewModel, navController, scaffoldState, coroutineScope)
    for(i in subjects){
         subjectViewModel.subjects+= listOf(Subject(i.id,i.queueName,profileViewModel.currentProfile, listOf(profileViewModel.currentProfile)))
    }
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage = "Очереди") },
        scaffoldState = scaffoldState,
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.93f)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                items(count = 1) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(100.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Очереди",
                            fontSize = 30.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        if (admin) {
                            CustomButton(
                                action = { navController.navigate(Routes.CreateQueue.route) },
                                name = "Создать очередь",
                                color = Color.Green,
                                height = 40.dp,
                                width = 150.dp,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                itemsIndexed(subjectViewModel.subjects) { _, item ->
                    SubjectCard(
                        subject = item,
                        scaffoldState = scaffoldState,
                        coroutineScope = coroutineScope,
                        deleteSubject = deleteSubject,
                        subjectViewModel = subjectViewModel,
                        profileViewModel = profileViewModel,
                        navController = navController
                    )
                }
            }
        },
        backgroundColor = Color(0xFF26264C)// Set background color to avoid the white flashing when you switch between screens
    )
}