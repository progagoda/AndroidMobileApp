package com.example.studhelper.screens.mainFrames.student.myGroup


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
import com.example.studhelper.R
import com.example.studhelper.components.BottomMenu
import com.example.studhelper.components.GroupmateCard

@Composable
fun MyGroup(navController: NavHostController) {
    val subject = arrayListOf<String>(
        "Байрамова Хумай Бахруp Кызы",
        "Балканский Андрей Александрович",
        "Перл Иван Алексеевич",
    )
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage = "Моя группа") },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.93f)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                items(count = 1) {
                    Text(
                        text = "Моя группа",
                        fontSize = 30.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                itemsIndexed(subject) { index, item ->
                    GroupmateCard(image = R.drawable.avatart, name = item )
                }
            }
        },
        backgroundColor = Color(0xFF26264C) // Set background color to avoid the white flashing when you switch between screens
    )
}
