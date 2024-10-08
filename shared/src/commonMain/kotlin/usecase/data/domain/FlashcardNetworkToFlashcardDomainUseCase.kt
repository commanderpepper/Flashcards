package usecase.data.domain

import models.data.FlashcardDomain
import models.data.FlashcardNetwork

class FlashcardNetworkToFlashcardDomainUseCase {
    operator fun invoke(flashcardNetwork: FlashcardNetwork): FlashcardDomain {
        return FlashcardDomain(id = flashcardNetwork.id, front = flashcardNetwork.front, back = flashcardNetwork.back)
    }
}