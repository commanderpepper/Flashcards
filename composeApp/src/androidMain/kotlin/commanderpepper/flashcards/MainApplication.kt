package commanderpepper.flashcards

import android.app.Application
import models.viewmodels.di.viewModelModule
import network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import usecase.di.useCaseModule

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            //add modules here
            modules(listOf(viewModelModule, networkModule, useCaseModule))
        }
    }
}