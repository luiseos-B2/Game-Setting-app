package com.example.gameapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameapp.Res
import com.example.gameapp.btn_exit
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExitButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CutCornerShape(topStart = 10.dp, topEnd = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4FF5F)),
        border = BorderStroke(3.dp, Color.Black),
        elevation = null,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(55.dp)
    ) {
        Text(
            stringResource(Res.string.btn_exit),
            color = Color.Black,
            style =
                TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic
            )
        )
    }
}