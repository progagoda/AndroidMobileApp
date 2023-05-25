package com.example.studhelper.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studhelper.InterFamily
import com.example.studhelper.data.Subject
import com.example.studhelper.funtions.QueueAction
import com.example.studhelper.funtions.goToQueue
import com.example.studhelper.funtions.loadGroup
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import com.example.studhelper.screens.mainFrames.student.queue.SubjectViewModel
import kotlinx.coroutines.CoroutineScope

@SuppressLint("SuspiciousIndentation")
@Composable
fun SubjectCard(subject: Subject, deleteSubject: (Subject)->Unit, subjectViewModel: SubjectViewModel, profileViewModel: ProfileViewModel, navController: NavHostController, scaffoldState: ScaffoldState, coroutineScope: CoroutineScope) {
    var color = Color.Green
    val allCount = loadGroup(profileViewModel).size
    val currentCount = subject.students.size
    val result = currentCount.toDouble()/(allCount.toDouble())
        if (result >= 0.5) {
            color = Color(0xFFB5B902)
        }
        if (result >= 0.75) {
            color = Color.Red
        }
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(vertical = 5.dp).clickable { goToQueue(subject,navController,subjectViewModel) },
        shape = RoundedCornerShape(15)
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF262A34), Color(0xFF26264C)
                    )
                )
            )
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = subject.name,
                            fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                    Column(
                        Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            5.dp,
                            Alignment.CenterVertically
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight(0.4f)
                                .background(color, shape = RoundedCornerShape(30)),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Text(
                                text = "$currentCount/$allCount",
                                fontFamily = InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                            if (profileViewModel.currentProfile.admin) {
                                Column(modifier = Modifier.clickable { QueueAction(profileViewModel).deleteQueue(subject.id, profileViewModel, subjectViewModel, navController,scaffoldState, coroutineScope) }) {
                                CustomButton(
                                    action = {deleteSubject(subject)},
                                    name = "X",
                                    color = Color.Red,
                                    width = 60.dp,
                                    height = 20.dp,
                                    fontSize = 13.sp,
                                    padding = PaddingValues(1.dp)
                                )
                            }
                        }
                    }
                }
                if (result != null) {
                    LinearProgressIndicator(
                        progress = result.toFloat(),
                        modifier = Modifier.fillMaxWidth(),
                        color = color,
                        backgroundColor = Color.White
                    )
                }
            }
        }
    }
}