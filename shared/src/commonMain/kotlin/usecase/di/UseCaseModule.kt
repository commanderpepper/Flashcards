package usecase.di

import org.koin.dsl.module
import usecase.data.ui.DeckDomainToDeckListsItemUIUseCase
import usecase.data.network.DeckDomainToDeckNetworkUseCase
import usecase.data.domain.DeckNetworkToDeckDomainUseCase
import usecase.data.ui.FlashcardDomainToFlashcardItemUIUseCase
import usecase.data.network.FlashcardDomainToFlashcardNetworkUseCase
import usecase.data.domain.FlashcardNetworkToFlashcardDomainUseCase

val useCaseModule = module {
    single { DeckDomainToDeckNetworkUseCase(get()) }
    single { DeckNetworkToDeckDomainUseCase(get()) }

    single { FlashcardDomainToFlashcardNetworkUseCase() }
    single { FlashcardNetworkToFlashcardDomainUseCase() }

    single { DeckDomainToDeckListsItemUIUseCase() }
    single { FlashcardDomainToFlashcardItemUIUseCase() }
}