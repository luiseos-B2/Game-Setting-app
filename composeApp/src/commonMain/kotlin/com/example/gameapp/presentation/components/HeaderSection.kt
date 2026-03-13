package com.example.gameapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameapp.Res
import com.example.gameapp.ic_close
import com.example.gameapp.settings_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeaderSection(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(Res.string.settings_title),
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        Surface(
            onClick = onDismiss,
            shape = CircleShape,
            color = Color(0xFF2D404E),
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_close),
                contentDescription = "Fechar",
                tint = Color.White,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}