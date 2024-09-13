package usecase.di

import org.koin.dsl.module
import usecase.data.ui.DeckDomainToDeckListsItemUIUseCase
import usecase.data.network.DeckDomainToDeckNetworkUseCase
import usecase.data.domain.DeckNetworkToDeckDomainUseCase
import usecase.data.domain.EditDeckFlashCardItemToFlashcardDomainUseCase
import usecase.data.domain.EditDeckToDeckDomainUseCase
import usecase.data.ui.FlashcardDomainToFlashcardItemUIUseCase
import usecase.data.network.FlashcardDomainToFlashcardNetworkUseCase
import usecase.data.domain.FlashcardNetworkToFlashcardDomainUseCase
import usecase.data.ui.DeckDomainToEditDeckUseCase
import usecase.data.ui.FlashcardDomainToEditDeckFlashCardItemUseCase

val useCaseModule = module {
    single { DeckDomainToDeckNetworkUseCase(get()) }
    single { DeckNetworkToDeckDomainUseCase(get()) }

    single { FlashcardDomainToFlashcardNetworkUseCase() }
    single { FlashcardNetworkToFlashcardDomainUseCase() }

    single { DeckDomainToDeckListsItemUIUseCase() }
    single { FlashcardDomainToFlashcardItemUIUseCase() }

    single { DeckDomainToEditDeckUseCase(get()) }
    single { FlashcardDomainToEditDeckFlashCardItemUseCase() }

    single { EditDeckFlashCardItemToFlashcardDomainUseCase() }
    single { EditDeckToDeckDomainUseCase(get()) }
}