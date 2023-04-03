package com.example.studhelper.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studhelper.InterFamily
import com.example.studhelper.screens.mainFrames.student.NavigationBar

@Composable
fun BottomMenu(navController: NavController, currentPage: String){
    val items = listOf(
        NavigationBar.Queue,
        NavigationBar.MyGroup,
        NavigationBar.Profile
    )
    BottomNavigation(
        backgroundColor = Color(0XFF181A20),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(30.dp) ) },
                label = { Text(text = item.title, fontFamily = InterFamily, fontWeight = FontWeight.SemiBold) },
                selectedContentColor = Color(0xFFC25FFF),
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = item.title==currentPage,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}