package ui.decklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import flashcards.composeapp.generated.resources.Res
import flashcards.composeapp.generated.resources.ic_plus
import models.ui.decklist.DeckListsItemId
import models.ui.decklist.DeckListsItemUI
import models.ui.decklist.DeckListsScreenState
import models.viewmodels.DeckListsScreenViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ui.util.Loading

@Composable
fun DeckListsScreen(modifier: Modifier, viewModel: DeckListsScreenViewModel = koinViewModel<DeckListsScreenViewModel>(), onDeckClick: (DeckListsItemId) -> Unit, onNewDeck: () -> Unit){
    val state = viewModel.deckListsScreenState.collectAsState()
    DeckListsScreen(modifier = modifier, deckListsScreenState = state.value, onDeckClick = onDeckClick, onNewDeck = onNewDeck)
}

@Composable
fun DeckListsScreen(modifier: Modifier = Modifier, deckListsScreenState: DeckListsScreenState, onDeckClick: (DeckListsItemId) -> Unit, onNewDeck: () -> Unit){
    Scaffold(modifier = modifier, floatingActionButton = {
        Button(onClick = {
            onNewDeck()
        }){
            Image(
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = null
            )
        }
    }) {
        when(deckListsScreenState){
            DeckListsScreenState.Error -> {
                NoDeckList(modifier = modifier)
            }
            DeckListsScreenState.Loading -> {
                Loading(modifier = modifier)
            }
            is DeckListsScreenState.Success -> {
                DeckListsScreen(modifier = modifier, deckList = deckListsScreenState.decks, onDeckClick = onDeckClick)
            }
        }
    }
}

@Composable
fun DeckListsScreen(modifier: Modifier, deckList: List<DeckListsItemUI>, onDeckClick: (DeckListsItemId) -> Unit) {
    val verticalArrangement = Arrangement.spacedBy(8.dp)
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(8.dp), verticalArrangement = verticalArrangement) {
        items(items = deckList, key = {item -> item.id.idValue}){ item ->
            DeckListsItemUI(deckListsItemUI = item, onDeckClick = onDeckClick)
        }
    }
}

@Composable
fun NoDeckList(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "No decks found")
    }
}