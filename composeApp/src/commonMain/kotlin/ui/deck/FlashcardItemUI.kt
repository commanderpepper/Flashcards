package ui.deck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.ui.deck.FlashcardItem

@Composable
fun FlashcardItemUI(flashcardItem: FlashcardItem) {
    var isFlipped : Boolean by remember { mutableStateOf(false) }
    val textToDisplay = if (isFlipped) flashcardItem.back else flashcardItem.front

    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { isFlipped = isFlipped.not() }) {
        Column(
            modifier = Modifier.fillMaxWidth().requiredHeight(128.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier.padding(4.dp), text = textToDisplay)
        }
    }
}