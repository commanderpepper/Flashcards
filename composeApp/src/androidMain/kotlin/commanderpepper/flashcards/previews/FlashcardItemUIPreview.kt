package commanderpepper.flashcards.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import models.ui.deck.FlashcardItem
import ui.deck.FlashcardItemUI

@Preview(showBackground = true, showSystemUi = true)
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