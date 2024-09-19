package usecase.data.ui

import models.data.DeckDomain
import models.ui.deck.DeckScreenDeckUI

class DeckDomainToDeckScreenDeckUI(private val flashcardDomainToFlashcardItemUIUseCase: FlashcardDomainToFlashcardItemUIUseCase) {
    operator fun invoke(deckDomain: DeckDomain): DeckScreenDeckUI {
        return DeckScreenDeckUI(
            name = deckDomain.name,
            id = deckDomain.id,
            flashcards = deckDomain.cards.map { flashcardDomainToFlashcardItemUIUseCase(it) }
        )
    }
}