package models.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.FlashcardRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import models.ui.editdeck.EditDeck
import models.ui.editdeck.EditDeckFlashCardItem
import models.ui.editdeck.EditDeckState
import usecase.data.domain.EditDeckToDeckDomainUseCase
import usecase.data.ui.DeckDomainToEditDeckUseCase

class EditDeckScreenViewModel(
    private val repo: FlashcardRepo,
    private val handle: SavedStateHandle,
    private val deckDomainToEditDeckUseCase: DeckDomainToEditDeckUseCase,
    private val editDeckToDeckDomainUseCase: EditDeckToDeckDomainUseCase
) : ViewModel() {
    private var editDeck: EditDeck = EditDeck(deckId = "", deckName = "", flashCards = emptyList())
    private val deckId = handle.get<String?>("deckId")

    private val _editDeckState = MutableStateFlow<EditDeckState>(EditDeckState.Loading)
    val editDeckState = _editDeckState.asStateFlow()

    init {
        if(deckId != null){
            try {
                viewModelScope.launch {
                    val repoEditDeck = repo.getDeck(deckId)?.let { deckDomainToEditDeckUseCase(it) }
                    if(repoEditDeck != null){
                        editDeck = repoEditDeck
                        _editDeckState.value = EditDeckState.Success(editDeck)
                    }
                    else {
                        _editDeckState.value = EditDeckState.Error
                    }
                }
            }
            catch (e: Exception){
                _editDeckState.value = EditDeckState.Error
            }
        }
        else {
            _editDeckState.value = EditDeckState.Success(editDeck)
        }
    }

    fun addNewFlashCard(){
        val flashcards = editDeck.flashCards + listOf(EditDeckFlashCardItem("", "", ""))
        editDeck = editDeck.copy(flashCards = flashcards)
        _editDeckState.value = EditDeckState.Success(editDeck)
    }

    suspend fun saveDeck(){
        repo.updateDeck(editDeckToDeckDomainUseCase(editDeck))
    }

    fun onFlashcardSaveChanges(flashCardItem: EditDeckFlashCardItem){
        updateFlashCard(flashCardItem)
    }

    fun markFlashCardForDeletion(flashCardItem: EditDeckFlashCardItem){
        updateFlashCard(flashCardItem)
    }

    private fun updateFlashCard(flashCardItem: EditDeckFlashCardItem){
        val flashcards = editDeck.flashCards.filter { it.id != flashCardItem.id } + listOf(flashCardItem)
        editDeck = editDeck.copy(flashCards = flashcards)
        _editDeckState.value = EditDeckState.Success(editDeck)
    }
}