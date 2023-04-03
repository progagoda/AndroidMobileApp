package com.example.studhelper.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studhelper.InterFamily


@Composable
fun GroupmateCard(image: Int, name: String) {
    Card(
        elevation = 10.dp, modifier = Modifier.padding(vertical = 5.dp),
        backgroundColor = Color(0XFF262A34),
        shape = RoundedCornerShape(15)
    ) {
        Column(
        ) {
            Row(
                modifier = Modifier
                    .height(75.dp)
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
            Image(painter = painterResource(id = image),"Avatar")
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .padding(10.dp)
                ) {
                    Text(
                        text = name,
                        fontFamily = InterFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}