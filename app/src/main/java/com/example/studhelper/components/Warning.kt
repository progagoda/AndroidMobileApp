package com.example.studhelper.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.studhelper.InterFamily

@Composable
fun Warning(name:String){
    Text(text = name, color = Color.Red, fontFamily = InterFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
}