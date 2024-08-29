package ui.util

import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier = Modifier){
    CircularProgressIndicator(
        modifier = modifier.width(64.dp),
        color = Color.Red
    )
}