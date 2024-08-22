package commanderpepper.flashcards

import models.data.DeckNetwork

interface DeckSource {
    suspend fun getAllDecks(): List<DeckNetwork>

    suspend fun getDeck(deckId: String): DeckNetwork?

    suspend fun insertDeck(deckNetwork: DeckNetwork)
}