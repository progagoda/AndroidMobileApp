package com.example.studhelper.screens.mainFrames.student.queue


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studhelper.InterFamily
import com.example.studhelper.components.BottomMenu
import com.example.studhelper.components.GroupmateCard
import com.example.studhelper.funtions.QueueAction
import com.example.studhelper.funtions.checkStateStudent
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun SubjectQueue(
    navController: NavController,
    subjectViewModel: SubjectViewModel,
    profileViewModel: ProfileViewModel
) {
    val subscribers by rememberSaveable {
        mutableStateOf(subjectViewModel.currentSubject.students)
    }
    var state by rememberSaveable {
        mutableStateOf(
            checkStateStudent(
                subjectViewModel.currentSubject,
                profileViewModel.currentProfile
            )
        )
    }
    var buttonText = "Записаться"
    var buttonColor = Color.Green
    if (state) {
        buttonText = "Отписаться"
        buttonColor = Color.Red
    }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage = "Очереди") },
        scaffoldState = scaffoldState,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.93f)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = 1) {
                    subjectViewModel.currentSubject.name.let { it1 ->
                        Text(
                            text = it1,
                            fontSize = 30.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                itemsIndexed(subscribers) { index, item ->
                    GroupmateCard(image = item.avatar, name = item.fullName, profileViewModel)

                }
                items(count = 1) {
                    AnimatedVisibility(visible = state,
                    enter= slideInHorizontally()+ fadeIn()
                    ) {
                        GroupmateCard(
                            image = profileViewModel.currentProfile.avatar,
                            name = profileViewModel.currentProfile.fullName,
                            profileViewModel
                        )
                    }
                    Button(
                        onClick = {
                            if (!state) {
                                QueueAction(profileViewModel).enterQueue(subjectViewModel.currentSubject.id,profileViewModel,scaffoldState, coroutineScope )
//                                subjectViewModel.subscribe(
//                                    profileViewModel.currentProfile,
//                                    subjectViewModel.currentSubject
//                                )
                                state = !state} else{
                                QueueAction(profileViewModel).quitQueue(subjectViewModel.currentSubject.id,profileViewModel,scaffoldState, coroutineScope )
                                subjectViewModel.unsubscribe(
                                    profileViewModel.currentProfile,
                                    subjectViewModel.currentSubject
                                )
                                state = !state}

                        },
                        modifier = Modifier
                            .height(40.dp)
                            .width(250.dp),
                        shape = RoundedCornerShape(30),
                        contentPadding = PaddingValues(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = buttonColor
                        ),

                        ) {
                        Text(
                            text = buttonText,
                            fontSize = 15.sp,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        },
        backgroundColor = Color(0xFF26264C) // Set background color to avoid the white flashing when you switch between screens
    )
}
