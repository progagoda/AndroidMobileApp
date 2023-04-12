package com.example.studhelper.screens.mainFrames.student.queue


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.studhelper.funtions.checkStateStudent
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun SubjectQueue(
    navController: NavController,
    subjectViewModel: SubjectViewModel,
    profileViewModel: ProfileViewModel
) {
    val subscribers = subjectViewModel.currentSubject.students
    val state = checkStateStudent(subjectViewModel.currentSubject, profileViewModel.currentProfile)
    var buttonText = "Записаться"
    var buttonColor = Color.Green
    if (state) {
        buttonText = "Отписаться"
        buttonColor = Color.Red
    }
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage = "Очереди") },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.93f)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                items(count = 1) {
                    Text(
                        text = subjectViewModel.currentSubject.name,
                        fontSize = 30.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Button(
                        onClick = { /*TODO*/ }, modifier = Modifier
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
                itemsIndexed(subscribers) { index, item ->
                    GroupmateCard(image = item.avatar, name = item.name)
                }
            }
        },
        backgroundColor = Color(0xFF26264C) // Set background color to avoid the white flashing when you switch between screens
    )
}
