import commanderpepper.flashcards.DeckSourceImpl
import kotlinx.coroutines.test.runTest
import models.data.DeckNetwork
import models.data.FlashcardNetwork
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class DeckSourceImplTest {

    private val deckNetwork = DeckSourceImpl()
    private val flashcards = listOf(FlashcardNetwork("1","A", "B"))
    private val testDeck = DeckNetwork("1", "test one", flashcards)

    @Test
    fun getAllDecks() = runTest {
        deckNetwork.insertDeck(testDeck)
        val decks = deckNetwork.getAllDecks()
        Assertions.assertTrue(decks.size == 1)
    }

    @Test
    fun getDeck() = runTest {
        deckNetwork.insertDeck(testDeck)
        val deck = deckNetwork.getDeck("1")
        Assertions.assertNotNull(deck)
    }

    @Test
    fun insertDeck() = runTest {
        deckNetwork.insertDeck(testDeck)
        val deck = deckNetwork.getDeck("1")
        Assertions.assertNotNull(deck)
        Assertions.assertTrue(deck?.cards?.size == 1)

        deckNetwork.insertDeck(testDeck.copy(cards = emptyList()))
        val emptyDeck = deckNetwork.getDeck("1")
        Assertions.assertNotNull(emptyDeck)
        Assertions.assertTrue(emptyDeck?.cards?.size == 0)
    }
}