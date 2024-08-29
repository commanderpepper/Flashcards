package usecase

import models.data.DeckDomain
import models.data.DeckNetwork

class DeckNetworkToDeckDomainUseCase(private val flashcardNetworkToFlashcardDomainUseCase: FlashcardNetworkToFlashcardDomainUseCase) {
    operator fun invoke(deckNetwork: DeckNetwork): DeckDomain{
        return DeckDomain(
            id = deckNetwork.id,
            name = deckNetwork.name,
            cards = deckNetwork.cards.map { card -> flashcardNetworkToFlashcardDomainUseCase(card) }
        )
    }
}