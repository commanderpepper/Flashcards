package ui.editdeck

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import flashcards.composeapp.generated.resources.Res
import flashcards.composeapp.generated.resources.ic_back
import kotlinx.coroutines.launch
import models.ui.editdeck.EditDeck
import models.ui.editdeck.EditDeckFlashCardItem
import models.ui.editdeck.EditDeckState
import models.viewmodels.EditDeckScreenViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ui.deck.NoFlashcards
import ui.util.Loading

@Composable
fun EditDeckScreen(
    modifier: Modifier,
    onBackButton: (String) -> Unit,
    onSaveButton: (String) -> Unit,
    editDeckScreenViewModel: EditDeckScreenViewModel = koinViewModel<EditDeckScreenViewModel>()
){
    val editDeckState = editDeckScreenViewModel.editDeckState.collectAsState()
    val scope = rememberCoroutineScope()
    EditDeckScreen(
        modifier = modifier,
        editDeckState = editDeckState.value,
        addNewFlashCard = editDeckScreenViewModel::addNewFlashCard,
        saveDeck = { deck, deckName ->
            scope.launch {
                editDeckScreenViewModel.saveDeck(deckName)
                onSaveButton(deck.deckId)
            }
        },
        onBackButton = onBackButton,
        onFlashcardSaveChanges = editDeckScreenViewModel::onFlashcardSaveChanges,
        markFlashCardForDeletion = editDeckScreenViewModel::markFlashCardForDeletion
    )
}

@Composable
fun EditDeckScreen(
    modifier: Modifier,
    editDeckState: EditDeckState,
    addNewFlashCard: () -> Unit,
    saveDeck: (EditDeck, String) -> Unit,
    onBackButton: (String) -> Unit,
    onFlashcardSaveChanges: (EditDeckFlashCardItem) -> Unit,
    markFlashCardForDeletion: (EditDeckFlashCardItem) -> Unit
){
    when(editDeckState){
        is EditDeckState.Success -> {
            EditDeckScreen(
                modifier = modifier,
                editDeck = editDeckState.editDeck,
                addNewFlashCard = addNewFlashCard,
                saveDeck = saveDeck,
                onBackButton = onBackButton,
                onFlashcardSaveChanges = onFlashcardSaveChanges,
                markFlashCardForDeletion = markFlashCardForDeletion
            )
        }
        EditDeckState.Error -> {
            NoFlashcards(modifier = modifier)
        }
        EditDeckState.Loading -> {
            Loading(modifier = modifier)
        }
    }
}

@Composable
fun EditDeckScreen(
    modifier: Modifier,
    editDeck: EditDeck,
    addNewFlashCard: () -> Unit,
    saveDeck: (EditDeck, String) -> Unit,
    onBackButton: (String) -> Unit,
    onFlashcardSaveChanges: (EditDeckFlashCardItem) -> Unit,
    markFlashCardForDeletion: (EditDeckFlashCardItem) -> Unit
) {
    var deckName by remember { mutableStateOf(editDeck.deckName) }

    Column(modifier = modifier.fillMaxSize().padding(8.dp)) {
        OutlinedTextField(value = deckName, onValueChange = { deckName = it }, label = { Text("Deck name") })
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {
                onBackButton(editDeck.deckId)
            }){
                Image(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = null
                )
            }
            Button(onClick = { addNewFlashCard() }){
                Text("Add Flashcard")
            }
            Button(onClick = {
                saveDeck(editDeck, deckName)
            }){
                Text("Save Deck")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = editDeck.flashCards, key = { item -> item.id }){ flashcard ->
                EditDeckFlashCardItemUI(
                    editDeckFlashCardItem = flashcard,
                    onFlashcardSaveChanges = onFlashcardSaveChanges,
                    markFlashCardForDeletion = markFlashCardForDeletion
                )
            }
        }
    }
}