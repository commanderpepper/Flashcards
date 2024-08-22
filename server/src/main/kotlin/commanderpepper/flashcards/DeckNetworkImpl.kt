package commanderpepper.flashcards

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import models.data.DeckNetwork

class DeckNetworkImpl(): DeckSource {

    private val decks = mutableMapOf<String, DeckNetwork>()
    private val mutex = Mutex()

    override suspend fun getAllDecks(): List<DeckNetwork> = withContext(Dispatchers.IO) {
        return@withContext mutex.withLock {
            decks.values.toList()
        }
    }

    override suspend fun getDeck(deckId: String): DeckNetwork? = withContext(Dispatchers.IO){
        return@withContext mutex.withLock {
            decks[deckId]
        }
    }

    override suspend fun insertDeck(deckNetwork: DeckNetwork) = withContext(Dispatchers.IO){
        mutex.withLock {
            decks[deckNetwork.id] = deckNetwork
        }
    }
}