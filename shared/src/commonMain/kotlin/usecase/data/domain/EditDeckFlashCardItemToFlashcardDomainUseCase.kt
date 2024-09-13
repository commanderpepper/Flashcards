package usecase.data.domain

import models.data.FlashcardDomain
import models.ui.editdeck.EditDeckFlashCardItem

class EditDeckFlashCardItemToFlashcardDomainUseCase {
    operator fun invoke(editDeckFlashCardItem: EditDeckFlashCardItem): FlashcardDomain {
        return FlashcardDomain(
            id = editDeckFlashCardItem.id,
            front = editDeckFlashCardItem.front,
            back = editDeckFlashCardItem.back
        )
    }
}