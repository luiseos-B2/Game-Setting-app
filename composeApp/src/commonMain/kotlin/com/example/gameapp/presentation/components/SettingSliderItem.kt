package com.example.gameapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingSliderItem(label: String, value: Float, onValueChange: (Float) -> Unit, activeColor: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 4.dp)
        )

        Box(modifier = Modifier.fillMaxWidth().height(20.dp).background(Color.Black)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(value)
                    .fillMaxHeight()
                    .background(activeColor)
            )
            Slider(
                value = value,
                onValueChange = onValueChange,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = Color.Transparent,
                    inactiveTrackColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}