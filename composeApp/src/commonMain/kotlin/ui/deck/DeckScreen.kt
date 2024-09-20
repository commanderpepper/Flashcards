package ui.deck

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import flashcards.composeapp.generated.resources.Res
import flashcards.composeapp.generated.resources.ic_back
import models.ui.deck.DeckScreenDeckUI
import models.ui.deck.DeckScreenState
import models.viewmodels.DeckScreenViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ui.util.Loading

@Composable
fun DeckScreen(modifier: Modifier, deckScreenViewModel: DeckScreenViewModel = koinViewModel<DeckScreenViewModel>(), onBackButton: () -> Unit, onEditDeck: (String) -> Unit){
    val deckScreenState = deckScreenViewModel.deckScreenState.collectAsState()
    DeckScreen(modifier = modifier, deckScreenState = deckScreenState.value, onBackButton = onBackButton, onEditDeck = onEditDeck)
}

@Composable
fun DeckScreen(modifier: Modifier, deckScreenState: DeckScreenState, onBackButton: () -> Unit, onEditDeck: (String) -> Unit) {
    Scaffold(modifier = modifier, topBar = {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(modifier = Modifier.padding(8.dp), onClick = {
                onBackButton()
            }){
                Image(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null
                )
            }
        }
    }) { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())) {
            when (deckScreenState) {
                is DeckScreenState.Success -> {
                    DeckScreen(modifier = Modifier.fillMaxSize(), deckScreenDeckUI = deckScreenState.deckScreenDeckUI, onEditDeck = onEditDeck)
                }

                DeckScreenState.Loading -> {
                    Loading(modifier = Modifier)
                }

                DeckScreenState.Error -> {
                    NoFlashcards(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun DeckScreen(modifier: Modifier, deckScreenDeckUI: DeckScreenDeckUI, onEditDeck: (String) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.padding(8.dp), text = deckScreenDeckUI.name)
            Button(modifier = Modifier.padding(8.dp), onClick = {
                onEditDeck(deckScreenDeckUI.id)
            }){
                Text("Edit Deck")
            }
        }

        Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            deckScreenDeckUI.flashcards.forEach { flashcardItem ->
                FlashcardItemUI(flashcardItem = flashcardItem)
            }
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