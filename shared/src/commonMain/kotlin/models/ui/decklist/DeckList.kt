package models.ui.decklist

import kotlin.jvm.JvmInline

@JvmInline
value class DeckListsItemName(val nameValue: String)

@JvmInline
value class DeckListsItemId(val idValue: String)

data class DeckListsItemUI(val name: DeckListsItemName, val id: DeckListsItemId)