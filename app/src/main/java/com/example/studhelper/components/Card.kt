package com.example.studhelper.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studhelper.InterFamily


@Composable
fun Card(name: String, currentCount: Int, allCount: Int) {
    var color = Color.Green
    var result = currentCount.toDouble() / allCount
    if (result >= 0.5) {
        color = Color(0xFFB5B902)
    }
    if (result >= 0.75) {
        color = Color.Red
    }
    Card(
        elevation = 10.dp, modifier = Modifier.padding(vertical = 5.dp),
        shape = RoundedCornerShape(15)
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF262A34),
                        Color(0xFF26264C)
                    )
                )
            )
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                ) {
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
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(0.4f)
                            .background(color, shape = RoundedCornerShape(30)),
                        horizontalAlignment = Alignment.CenterHorizontally

                    )
                    {
                        Text(
                            text = "$currentCount/$allCount", fontFamily = InterFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }

                }
                LinearProgressIndicator(
                    progress = result.toFloat(),
                    modifier = Modifier.fillMaxWidth(),
                    color = color,
                    backgroundColor = Color.White
                )
            }
        }
    }
}