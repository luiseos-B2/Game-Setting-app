package com.example.gameapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gameapp.Res
import com.example.gameapp.ic_arrow_default
import com.example.gameapp.label_language
import com.example.gameapp.presentation.SettingsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LanguageSelector(viewModel: SettingsViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(Res.string.label_language),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            IconButton(onClick = { viewModel.previousLanguage() }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_default),
                    contentDescription = "Previous Language",
                    tint = Color.Unspecified
                )
            }

            Text(
                text = state.language.displayName,
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            IconButton(onClick = { viewModel.nextLanguage() }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_default),
                    contentDescription = "Next Language",
                    tint = Color.Unspecified,
                    modifier = Modifier.rotate(180f)
                )
            }
        }
    }
}