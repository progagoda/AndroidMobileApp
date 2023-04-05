package com.example.studhelper.components
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studhelper.InterFamily

@Composable
fun CustomButton(action:()->Unit = {}, actionArr: Array<()->Unit> = arrayOf(), name:String, color: Color= Color(0xFF246BFD), height:Dp = 40.dp, width:Dp=250.dp, fontSize: TextUnit= 15.sp, padding: PaddingValues = PaddingValues(10.dp)){
    Button(onClick = {
        if (actionArr.isEmpty()){
            action
        }
         else actionArr.forEach() { function->function() }
    },
        modifier = Modifier
            .height(height)
            .width(width),
        shape =   RoundedCornerShape(30),
        contentPadding = padding,
        colors = ButtonDefaults.buttonColors(backgroundColor = color
        )) {
        Text(text=name, fontSize = fontSize,fontFamily = InterFamily, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
    }
}