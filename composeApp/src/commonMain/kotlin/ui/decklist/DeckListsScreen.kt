package ui.decklist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.ui.DeckId
import models.ui.DeckListsItem

@Composable
fun DeckListsScreen(modifier: Modifier = Modifier){

}

@Composable
fun DeckListsScreen(deckList: List<DeckListsItem>, onDeckClick: (DeckId?) -> Unit) {
    val verticalArrangement = Arrangement.spacedBy(8.dp)
    LazyColumn(contentPadding = PaddingValues(8.dp), verticalArrangement = verticalArrangement) {
        items(items = deckList, key = {item -> item.id}){ item ->
            DeckListsItemUI(deckListsItem = item, onDeckClick = onDeckClick)
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