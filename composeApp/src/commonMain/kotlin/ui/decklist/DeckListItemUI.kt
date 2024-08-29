package ui.decklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.ui.decklist.DeckListsItemId
import models.ui.decklist.DeckListsItemUI
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DeckListsItemUI(deckListsItemUI: DeckListsItemUI, onDeckClick: (DeckListsItemId) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().height(128.dp).clickable { onDeckClick(deckListsItemUI.id) }) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = deckListsItemUI.name.nameValue)
        }
    }
}

@Preview
@Composable
private fun DeckListsItemUIPreview() {
    DeckListsItemUI(deckListsItemUI = SAMPLE_DECK_LIST_SAMPLE_ONE, onDeckClick = {})
}