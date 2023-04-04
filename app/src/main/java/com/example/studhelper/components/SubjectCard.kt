package com.example.studhelper.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
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
import com.example.studhelper.InterFamily
import com.example.studhelper.data.Subject


@Composable
fun SubjectCard(subject: Subject, currentCount: Int, allCount: Int, admin: Boolean, deleteSubject: (Subject)->Unit) {
    var color = Color.Green
    var result = currentCount.toDouble() / allCount
    if (result >= 0.5) {
        color = Color(0xFFB5B902)
    }
    if (result >= 0.75) {
        color = Color.Red
    }
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(vertical = 5.dp),
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
                            if (admin) {
                                Column(modifier = Modifier.clickable { deleteSubject(subject) }) {
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