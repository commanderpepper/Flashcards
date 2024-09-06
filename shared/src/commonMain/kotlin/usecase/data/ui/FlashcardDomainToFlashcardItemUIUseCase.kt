package usecase.data.ui

import models.data.FlashcardDomain
import models.ui.deck.FlashcardItem

class FlashcardDomainToFlashcardItemUIUseCase {
    operator fun invoke(flashcardDomain: FlashcardDomain): FlashcardItem {
        return FlashcardItem(front = flashcardDomain.front, back = flashcardDomain.back)
    }
}