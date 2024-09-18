package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import models.data.DeckNetwork

class FlashcardDataSourceImpl(private val client: HttpClient): FlashcardDataSource {
    override suspend fun getFlashcards(): List<DeckNetwork> {
        return client.get("/decks").body<List<DeckNetwork>>()
    }

    override suspend fun getFlashcard(deckId: String): DeckNetwork? {
        return client.get("/deck/$deckId").body<DeckNetwork?>()
    }

    override suspend fun updateFlashcard(deckNetwork: DeckNetwork) {
        client.post("/deck"){
            contentType(ContentType.Application.Json)
            setBody(deckNetwork)
        }
    }
}