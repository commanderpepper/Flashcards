package ui.deck

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import models.ui.deck.DeckScreenState
import models.ui.deck.FlashcardItem
import ui.util.Loading

@Composable
fun DeckScreen(modifier: Modifier, deckScreenState: DeckScreenState) {
    when (deckScreenState) {
        is DeckScreenState.Success -> {
            DeckScreen(modifier = modifier, flashcards = deckScreenState.flashcards)
        }

        DeckScreenState.Loading -> {
            Loading(modifier = modifier)
        }

        DeckScreenState.Error -> {
            NoFlashcards(modifier = modifier)
        }
    }
}

@Composable
fun DeckScreen(modifier: Modifier, flashcards: List<FlashcardItem>) {
    Column(modifier = modifier.fillMaxSize().horizontalScroll(rememberScrollState())) {
        flashcards.forEach { flashcardItem ->
            FlashcardItemUI(flashcardItem = flashcardItem)
        }
    }
}

@Composable
fun NoFlashcards(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Deck couldn't be found?")
    }
}