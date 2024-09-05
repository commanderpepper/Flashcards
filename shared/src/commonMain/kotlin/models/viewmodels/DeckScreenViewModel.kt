package models.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.FlashcardRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import models.ui.deck.DeckScreenState
import models.ui.deck.FlashcardItem

class DeckScreenViewModel(private val flashcardRepo: FlashcardRepo): ViewModel() {
    private val _deckScreenState : MutableStateFlow<DeckScreenState> = MutableStateFlow(DeckScreenState.Loading)
    val deckScreenState: StateFlow<DeckScreenState> = _deckScreenState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = DeckScreenState.Loading
    )

    init {
        try {
            viewModelScope.launch {
                val deck = flashcardRepo.getDeck("1")
                if(deck != null){
                    val flashcards = deck.cards.map {
                        FlashcardItem(front = it.front, back = it.back)
                    }
                    if(flashcards.isNotEmpty()){
                        _deckScreenState.value = DeckScreenState.Success(flashcards)
                    }
                    else {
                        _deckScreenState.value = DeckScreenState.Error
                    }
                }
            }
        }
        catch (e: Exception){
            _deckScreenState.value = DeckScreenState.Error
        }
    }
}