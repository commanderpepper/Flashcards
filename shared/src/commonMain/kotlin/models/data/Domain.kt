package models.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeckDomain(
    val id: String,
    val name: String,
    val cards: List<FlashcardDomain>
)

@Serializable
data class FlashcardDomain(val id: String, val front: String, val back: String)
