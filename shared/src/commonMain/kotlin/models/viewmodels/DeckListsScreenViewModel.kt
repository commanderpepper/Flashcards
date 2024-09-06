package models.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import domain.FlashcardRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import models.ui.decklist.DeckListsScreenState
import usecase.data.ui.DeckDomainToDeckListsItemUIUseCase

class DeckListsScreenViewModel(private val flashcardRepo: FlashcardRepo, private val deckDomainToDeckListsItemUIUseCase: DeckDomainToDeckListsItemUIUseCase): ViewModel() {
    private val _deckListsScreenState: MutableStateFlow<DeckListsScreenState> = MutableStateFlow(DeckListsScreenState.Loading)
    val deckListsScreenState: StateFlow<DeckListsScreenState> = _deckListsScreenState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val decks = flashcardRepo.getDecks()
                if(decks.isNotEmpty()){
                    _deckListsScreenState.value = DeckListsScreenState.Success(decks = decks.map {
                        deckDomainToDeckListsItemUIUseCase(it)
                    })
                }
                else {
                    _deckListsScreenState.value = DeckListsScreenState.Error
                }
            }
            catch(e: Exception){
                Logger.w("Humza", e.cause) {
                    e.message ?: ""
                }
                _deckListsScreenState.value = DeckListsScreenState.Error
            }
        }
    }
}