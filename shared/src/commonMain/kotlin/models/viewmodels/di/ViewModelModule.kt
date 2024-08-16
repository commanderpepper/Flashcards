package models.viewmodels.di

import models.viewmodels.DeckListsScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DeckListsScreenViewModel() }
}