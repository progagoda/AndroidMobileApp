package com.example.studhelper.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.example.studhelper.components.Input

@Composable
fun Login(navController: NavHostController){
    Column(
        modifier = Modifier
            .background(Color(0xFF313030))
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Input(name = "Login")
        Input(name = "Password")
        Button(onClick = {},
            modifier = Modifier.height(40.dp).width(250.dp),
            shape =   RoundedCornerShape(30),
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF246BFD)
            )) {
            Text("Login", fontSize = 15.sp,fontFamily = InterFamily, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Button(onClick = {},
            modifier = Modifier.height(40.dp).width(250.dp),
            shape =   RoundedCornerShape(30),
            contentPadding = PaddingValues(10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF246BFD))) {
            Text("Register", fontSize = 15.sp, fontFamily = InterFamily, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}