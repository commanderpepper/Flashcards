package network

import models.data.DeckNetwork
import models.data.FlashcardNetwork

interface FlashcardDataSource {
    suspend fun getFlashcards(): List<DeckNetwork>

    suspend fun getFlashcard(deckId: String): DeckNetwork?

    suspend fun updateFlashcard(deckNetwork: DeckNetwork)
}