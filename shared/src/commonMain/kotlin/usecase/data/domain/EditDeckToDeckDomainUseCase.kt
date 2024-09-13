package usecase.data.domain

import models.data.DeckDomain
import models.ui.editdeck.EditDeck

class EditDeckToDeckDomainUseCase(private val editDeckFlashCardItemToFlashcardDomainUseCase: EditDeckFlashCardItemToFlashcardDomainUseCase) {
    operator fun invoke(editDeck: EditDeck): DeckDomain {
        return DeckDomain(
            id = editDeck.deckId,
            name = editDeck.deckName,
            cards = editDeck.flashCards.map {
                editDeckFlashCardItemToFlashcardDomainUseCase(it)
            }
        )
    }
}