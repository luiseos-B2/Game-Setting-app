package com.example.gameapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gameapp.Res
import com.example.gameapp.label_audio
import com.example.gameapp.label_music
import com.example.gameapp.presentation.components.ExitButton
import com.example.gameapp.presentation.components.HeaderSection
import com.example.gameapp.presentation.components.LanguageSelector
import com.example.gameapp.presentation.components.SettingSliderItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsModal(
    viewModel: SettingsViewModel,
    onDismiss: () -> Unit
) {
    val settingsModel by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
            .clickable(onClick = onDismiss, indication = null, interactionSource = remember { MutableInteractionSource() }),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .aspectRatio(1.1f)
                .clickable(enabled = false) { }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF2D404E), RoundedCornerShape(4.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(4.dp))
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderSection(onDismiss)

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SettingSliderItem(
                        label = stringResource(Res.string.label_audio),
                        value = settingsModel.sfxVolume,
                        onValueChange = viewModel::changeSfxVolume,
                        activeColor = Color(0xFF00A381)
                    )
                    SettingSliderItem(
                        label = stringResource(Res.string.label_music),
                        value = settingsModel.musicVolume,
                        onValueChange = viewModel::changeMusicVolume,
                        activeColor = Color.White
                    )
                    LanguageSelector(viewModel)
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 25.dp)
            ) {
                ExitButton(onClick = onDismiss)
            }
        }
    }
}