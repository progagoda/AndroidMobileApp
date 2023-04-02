package com.example.studhelper.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.studhelper.InterFamily
import com.example.studhelper.R

@Composable
fun Input(name:String){
    var input by rememberSaveable{
        mutableStateOf("")
    }
    var passwordVisibility by rememberSaveable{
        mutableStateOf(false)
    }
    OutlinedTextField(value = input , onValueChange ={
        input= it
    },
        placeholder = { Text(text = name, fontFamily = InterFamily, fontWeight = FontWeight.SemiBold, color = Color.White) },
        label= { Text(text = name,fontFamily = InterFamily, fontWeight = FontWeight.SemiBold, color = Color.White) },
        trailingIcon = {
            if (name == "Password") {
                val icon = if(passwordVisibility)
                    painterResource(id = R.drawable.design_ic_visibility)
                else painterResource(id = R.drawable.design_ic_visibility_off)
                IconButton(onClick = { passwordVisibility=!passwordVisibility}) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon",
                        modifier = Modifier.size(22.dp)
                    )

                }
            } else {}
        },
        visualTransformation = if(name == "Password") {
            if (passwordVisibility) {
                VisualTransformation.None}
            else PasswordVisualTransformation()
        }
        else VisualTransformation.None,
    )
}