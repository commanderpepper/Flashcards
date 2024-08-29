package domain

import models.data.DeckDomain
import network.FlashcardDataSource
import usecase.DeckDomainToDeckNetworkUseCase
import usecase.DeckNetworkToDeckDomainUseCase

class FlashcardRepoImpl(
    private val network: FlashcardDataSource,
    private val deckNetworkToDeckDomainUseCase: DeckNetworkToDeckDomainUseCase,
    private val deckDomainToDeckNetworkUseCase: DeckDomainToDeckNetworkUseCase) : FlashcardRepo {

    override suspend fun getDecks(): List<DeckDomain> {
        return network.getFlashcards().map { deckNetworkToDeckDomainUseCase(it) }
    }

    override suspend fun getDeck(deckId: String): DeckDomain? {
        return network.getFlashcard(deckId)?.let { deckNetworkToDeckDomainUseCase(it) }
    }

    override suspend fun updateDeck(deckDomain: DeckDomain) {
        network.updateFlashcard(deckDomainToDeckNetworkUseCase(deckDomain))
    }
}