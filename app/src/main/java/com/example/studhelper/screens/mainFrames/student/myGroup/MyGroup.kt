package com.example.studhelper.screens.mainFrames.student.myGroup


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studhelper.InterFamily
import com.example.studhelper.components.BottomMenu
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.GroupmateCard
import com.example.studhelper.data.GroupCreds
import com.example.studhelper.funtions.GroupAction
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun MyGroup(navController: NavHostController, profileViewModel: ProfileViewModel, groupViewModel: GroupViewModel) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var currentGroup:GroupCreds = GroupAction(profileViewModel).getGroup(scaffoldState, coroutineScope)
    var profiles = GroupAction(profileViewModel).getStudent()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController, currentPage = "Моя группа") },
        scaffoldState = scaffoldState,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight(0.93f)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                items(count = 1) {
                    Text(
                        text = "Моя группа",
                        fontSize = 30.sp,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    if(profileViewModel.currentProfile.admin){
                        currentGroup.inviteCode?.let { it1 ->
                            OutlinedTextField(
                                value = it1,
                                onValueChange={},
                                textStyle = TextStyle(
                                    color = Color.White,
                                    fontFamily = InterFamily,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                label = {
                                    Text(
                                        text = "Код группы",
                                        fontFamily = InterFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )
                                })
                        }
                }
                }
                itemsIndexed(profiles) { index, item ->
                    GroupmateCard(image = item.avatar, name = item.fullName, profileViewModel )
                }
                items(count=1){
                    if (profileViewModel.currentProfile.admin){
                        CustomButton(name = "Удалить группу", action = { GroupAction(profileViewModel).deleteGroup(profileViewModel, groupViewModel, navController,scaffoldState, coroutineScope) }, color = Color.Cyan)
                    }}
                items(count=1){
                    CustomButton(name = "Выйти из группы", color = Color.Red, action = {GroupAction(profileViewModel).exitGroup(profileViewModel, navController,scaffoldState,coroutineScope)} )
                }
            }
        },
        backgroundColor = Color(0xFF26264C) // Set background color to avoid the white flashing when you switch between screens
    )
}
