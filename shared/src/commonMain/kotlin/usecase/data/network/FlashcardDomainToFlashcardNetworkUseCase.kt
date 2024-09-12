package usecase.data.network

import models.data.FlashcardDomain
import models.data.FlashcardNetwork

class FlashcardDomainToFlashcardNetworkUseCase {
    operator fun invoke(flashcardDomain: FlashcardDomain): FlashcardNetwork {
        return FlashcardNetwork(id = flashcardDomain.id, front = flashcardDomain.front, back = flashcardDomain.back)
    }
}