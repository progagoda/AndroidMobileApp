package com.example.studhelper.components
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studhelper.InterFamily

@Composable
fun BlueButton(action: ()-> Unit, name:String){
    Button(onClick = action,
        modifier = Modifier
            .height(40.dp)
            .width(250.dp),
        shape =   RoundedCornerShape(30),
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF246BFD)
        )) {
        Text(text=name, fontSize = 15.sp,fontFamily = InterFamily, fontWeight = FontWeight.Bold, color = Color.White)
    }
}