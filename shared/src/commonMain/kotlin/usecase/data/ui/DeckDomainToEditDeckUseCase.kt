package usecase.data.ui

import models.data.DeckDomain
import models.ui.editdeck.EditDeck

class DeckDomainToEditDeckUseCase(private val flashcardDomainToEditDeckFlashCardItemUseCase: FlashcardDomainToEditDeckFlashCardItemUseCase) {
    operator fun invoke(deckDomain: DeckDomain): EditDeck {
        return EditDeck(
            deckId = deckDomain.id,
            deckName = deckDomain.name,
            flashCards = deckDomain.cards.map {
                flashcardDomainToEditDeckFlashCardItemUseCase(it)
            }
        )
    }
}