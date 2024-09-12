package models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("FlashcardDeck")
data class DeckNetwork(
    val id: String,
    val name: String,
    @SerialName("flashcards")
    val cards: List<FlashcardNetwork>)

@Serializable
@SerialName("Flashcard")
data class FlashcardNetwork(val id: String, val front: String, val back: String)
