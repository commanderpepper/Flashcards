package models.viewmodels.di

import models.viewmodels.DeckListsScreenViewModel
import models.viewmodels.DeckScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DeckListsScreenViewModel(get(), get()) }
    viewModel { DeckScreenViewModel(get(), get(), get()) }
}