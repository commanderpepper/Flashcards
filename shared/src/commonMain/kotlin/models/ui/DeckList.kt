package models.ui

import kotlin.jvm.JvmInline

@JvmInline
value class DeckName(val nameValue: String)

@JvmInline
value class DeckId(val idValue: String)

data class DeckListsItem(val name: DeckName, val id: DeckId)