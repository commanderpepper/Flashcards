package usecase.data.ui

import models.data.DeckDomain
import models.ui.decklist.DeckListsItemId
import models.ui.decklist.DeckListsItemName
import models.ui.decklist.DeckListsItemUI

class DeckDomainToDeckListsItemUIUseCase {
    operator fun invoke(deckDomain: DeckDomain): DeckListsItemUI {
        return DeckListsItemUI(
            name = DeckListsItemName(deckDomain.name),
            id = DeckListsItemId(deckDomain.id)
        )
    }
}