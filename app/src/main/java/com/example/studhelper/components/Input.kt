package com.example.studhelper.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.studhelper.InterFamily
import com.example.studhelper.R

@Composable
fun Input(name: String, text: String, onTextChange: (text:String) -> Unit) {
    var passwordVisibility = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        textStyle = TextStyle(
            color = Color.White,
            fontFamily = InterFamily,
            fontWeight = FontWeight.SemiBold
        ),
        placeholder = {
            Text(
                text = name,
                fontFamily = InterFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        label = {
            Text(
                text = name,
                fontFamily = InterFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        trailingIcon = {
            if (name == "Password") {
                val icon = if (passwordVisibility.value)
                    painterResource(id = R.drawable.design_ic_visibility)
                else painterResource(id = R.drawable.design_ic_visibility_off)
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon",
                        modifier = Modifier.size(22.dp)
                    )

                }
            }
        },
        visualTransformation = if (name == "Password") {
            if (passwordVisibility.value) {
                VisualTransformation.None
            } else PasswordVisualTransformation()
        } else VisualTransformation.None,
    )
}