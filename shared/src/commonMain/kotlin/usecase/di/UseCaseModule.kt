package usecase.di

import org.koin.dsl.module
import usecase.DeckDomainToDeckNetworkUseCase
import usecase.DeckNetworkToDeckDomainUseCase
import usecase.FlashcardDomainToFlashcardNetworkUseCase
import usecase.FlashcardNetworkToFlashcardDomainUseCase

val useCaseModule = module {
    single { DeckDomainToDeckNetworkUseCase(get()) }
    single { DeckNetworkToDeckDomainUseCase(get()) }

    single { FlashcardDomainToFlashcardNetworkUseCase() }
    single { FlashcardNetworkToFlashcardDomainUseCase() }
}