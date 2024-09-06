package usecase.data.network

import models.data.DeckDomain
import models.data.DeckNetwork

class DeckDomainToDeckNetworkUseCase(private val flashcardDomainToFlashcardNetworkUseCase: FlashcardDomainToFlashcardNetworkUseCase) {
    operator fun invoke(deckDomain: DeckDomain): DeckNetwork {
        return DeckNetwork(
            id = deckDomain.id,
            name = deckDomain.name,
            cards = deckDomain.cards.map { flashcardDomainToFlashcardNetworkUseCase(it) }
        )
    }
}