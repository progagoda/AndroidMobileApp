package com.example.studhelper.screens.mainFrames.student.queue

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studhelper.InterFamily
import com.example.studhelper.components.CustomButton
import com.example.studhelper.components.Input
import com.example.studhelper.data.Subject
import com.example.studhelper.funtions.createQueue
import com.example.studhelper.screens.loginRegisterFrames.Routes
import com.example.studhelper.screens.mainFrames.student.myGroup.GroupViewModel
import com.example.studhelper.screens.mainFrames.student.profile.ProfileViewModel
import kotlinx.coroutines.launch
@Composable
fun CreateQueue(
    navController: NavController,
    addSubject: (Subject) -> Unit,
    profileViewModel: ProfileViewModel,
    groupViewModel: GroupViewModel
) {
    val input = remember {
        mutableStateOf("")
    }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF262A34),
                            Color(0xFF26264C)
                        )
                    )
                )
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Input(name = "Имя очереди", text = input.value , onTextChange = {input.value=it})
            CustomButton(name ="Добавить очередь", action={createQueue(input,profileViewModel,scaffoldState,groupViewModel, coroutineScope,navController,addSubject)})
        }
    }
}