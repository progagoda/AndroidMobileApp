package com.example.studhelper.screens.mainFrames.student.queue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun Queue(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    subjects: List<Subject>,
    deleteSubject: (Subject) -> Unit
) {
    val admin = profileViewModel.currentProfile.admin;// depends on user usual student or admin of group
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage = "Очереди") },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
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
                                action = {navController.navigate(Routes.CreateQueue.route)},
                                name = "Создать очередь",
                                color = Color.Green,
                                height = 40.dp,
                                width = 150.dp,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                itemsIndexed(subjects) { _, item ->
                    SubjectCard(
                        subject = item,
                        currentCount = (0..30).random(),
                        allCount = (25..30).random(),
                        admin = admin,
                        deleteSubject = deleteSubject
                    )
                }
            }
        },
        backgroundColor = Color(0xFF26264C)// Set background color to avoid the white flashing when you switch between screens
    )
}