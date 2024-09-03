package domain.di

import domain.FlashcardRepo
import domain.FlashcardRepoImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    single {
        FlashcardRepoImpl(
            network = get(),
            deckDomainToDeckNetworkUseCase = get(),
            deckNetworkToDeckDomainUseCase = get()
        )
    } bind (FlashcardRepo::class)
}