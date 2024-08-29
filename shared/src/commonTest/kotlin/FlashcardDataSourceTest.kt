import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import network.FlashcardDataSourceImpl
import kotlin.test.Asserter
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FlashcardDataSourceTest {
    @Test
    fun getDecksTest(){
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(
                        """
                [
                  {
                    "id": "1",
                    "name": "Test",
                    "flashcards": []
                  }
                ]
                """.trimIndent()
                    ),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val client = HttpClient(mockEngine) {
                install(ContentNegotiation) {
                    json()
                }
            }
            val source = FlashcardDataSourceImpl(client)
            val decks = source.getFlashcards()
            assertTrue(decks.isNotEmpty())
        }
    }

    @Test
    fun getDeck(){
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(
                        """
                {
                    "id": "1",
                    "name": "Test",
                    "flashcards": []
                }
                """.trimIndent()
                    ),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val client = HttpClient(mockEngine) {
                install(ContentNegotiation) {
                    json()
                }
            }
            val source = FlashcardDataSourceImpl(client)
            val deck = source.getFlashcard("1")
            assertNotNull(deck)
        }
    }
}