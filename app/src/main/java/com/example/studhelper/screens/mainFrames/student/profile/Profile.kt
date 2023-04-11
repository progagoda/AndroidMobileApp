package com.example.studhelper.screens.mainFrames.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studhelper.InterFamily
import com.example.studhelper.R
import com.example.studhelper.components.BottomMenu
import com.example.studhelper.components.CustomButton
import com.example.studhelper.funtions.logOut
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@Composable
fun Profile(navController: NavHostController,
profileViewModel: ProfileViewModel) {
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage= "Профиль") },
        backgroundColor = Color(0xFF26264C), // Set background color to avoid the white flashing when you switch between screens
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Column( modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()) {
                Text(
                    text = "Ваш Профиль",
                    fontSize = 30.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Box(
                    modifier= Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(painter = painterResource(id = R.drawable.avatar_bad_breaking), contentDescription ="Avatar",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxHeight(0.35f))
                }
               Column(
                   modifier=Modifier.fillMaxWidth().padding(vertical = 10.dp),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(text = profileViewModel.currentProfile.name, fontFamily = InterFamily, fontWeight = FontWeight.Bold, fontSize = 40.sp, color = Color.White)
                   Text(text = profileViewModel.currentProfile.group.name, fontFamily = InterFamily, fontSize = 20.sp,color = Color.White)
                   CustomButton(action = {logOut(profileViewModel,navController)}, name ="Выйти из профиля" )
               }

            }
        }
    )
}