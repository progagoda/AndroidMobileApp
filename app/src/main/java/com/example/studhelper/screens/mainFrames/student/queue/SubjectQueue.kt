package com.example.studhelper.screens.mainFrames.student.queue


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studhelper.InterFamily
import com.example.studhelper.data.Subject
import com.example.studhelper.screens.loginRegisterFrames.Routes
import kotlinx.coroutines.launch

@Composable
fun SubjectQueue(
    navController: NavController,
    addSubject: (Subject) -> Unit,
) {
    var input = remember {
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
            OutlinedTextField(
                value = input.value,
                onValueChange = {
                    input.value = it
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.SemiBold
                ),
                placeholder = {
                    Text(
                        text = "Имя очереди",
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                },
                label = {
                    Text(
                        text = "Имя очереди",
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                })
            Button(
                onClick = {
                    if (input.value == "") {
                        coroutineScope.launch{
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Поле не может быть пустым")
                        }
                    } else {
                        addSubject(Subject(name = input.value, group = "P33131"))
                        navController.navigate(Routes.Queue.route)
                    }
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(250.dp),
                shape = RoundedCornerShape(30),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF246BFD)
                )
            ) {
                Text(
                    text = "Add Queue",
                    fontSize = 15.sp,
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}