package commanderpepper.flashcards.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ui.decklist.DeckListsScreen
import ui.decklist.SAMPLE_DECK_LIST

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeckListsScreenPreview(){
    DeckListsScreen(modifier = Modifier.fillMaxSize(), SAMPLE_DECK_LIST){

    }
}