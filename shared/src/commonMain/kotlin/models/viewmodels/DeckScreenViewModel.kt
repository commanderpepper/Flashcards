package models.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import domain.FlashcardRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import models.ui.deck.DeckScreenState
import usecase.data.ui.DeckDomainToDeckScreenDeckUI
import usecase.data.ui.FlashcardDomainToFlashcardItemUIUseCase

class DeckScreenViewModel(
    private val flashcardRepo: FlashcardRepo,
    private val handle: SavedStateHandle,
    private val deckDomainToDeckScreenDeckUI: DeckDomainToDeckScreenDeckUI): ViewModel() {
    private val _deckScreenState : MutableStateFlow<DeckScreenState> = MutableStateFlow(DeckScreenState.Loading)
    val deckScreenState: StateFlow<DeckScreenState> = _deckScreenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = DeckScreenState.Loading
    )

    init {
        val id = handle.get<String>("deckId")
        Logger.i("Humza"){
            "The id is $id"
        }
        try {
            if(id != null){
                viewModelScope.launch {
                    val deck = flashcardRepo.getDeck(id)
                    if(deck != null){
                        if(deck.cards.isNotEmpty()){
                            _deckScreenState.value = DeckScreenState.Success(deckDomainToDeckScreenDeckUI(deck))
                        }
                        else {
                            _deckScreenState.value = DeckScreenState.Error
                        }
                    }
                }
            }
            else {
                _deckScreenState.value = DeckScreenState.Error
            }
        }
        catch (e: Exception){
            _deckScreenState.value = DeckScreenState.Error
        }
    }
}