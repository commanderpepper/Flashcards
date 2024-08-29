package domain

import models.data.DeckDomain

interface FlashcardRepo {
    suspend fun getDecks(): List<DeckDomain>

    suspend fun getDeck(deckId: String): DeckDomain?

    suspend fun updateDeck(deckDomain: DeckDomain)
}