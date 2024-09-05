package ui.deck

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.ui.deck.FlashcardItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FlashcardItemUI(flashcardItem: FlashcardItem) {
    var isFlipped : Boolean by remember { mutableStateOf(false) }
    val textToDisplay = if (isFlipped) flashcardItem.back else flashcardItem.front

    Column(
        modifier = Modifier
            .height(48.dp)
            .clickable { isFlipped = isFlipped.not() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(textToDisplay)
    }
}

@Preview
@Composable
fun FlashcardItemUIPreview() {
    Column {
        FlashcardItemUI(
            FlashcardItem(
                front = "This is the front of the card",
                back = "This is the back"
            )
        )
        FlashcardItemUI(
            FlashcardItem(
                front = "This is the front of the card",
                back = "This is the back"
            )
        )
        FlashcardItemUI(
            FlashcardItem(
                front = "This is the front of the card",
                back = "This is the back"
            )
        )
    }
}
