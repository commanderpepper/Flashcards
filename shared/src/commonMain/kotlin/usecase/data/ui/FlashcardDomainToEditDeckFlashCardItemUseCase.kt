package usecase.data.ui

import models.data.FlashcardDomain
import models.ui.editdeck.EditDeckFlashCardItem

class FlashcardDomainToEditDeckFlashCardItemUseCase {
    operator fun invoke(flashcardDomain: FlashcardDomain): EditDeckFlashCardItem {
        return EditDeckFlashCardItem(
            id = flashcardDomain.id,
            front = flashcardDomain.front,
            back = flashcardDomain.back
        )
    }
}