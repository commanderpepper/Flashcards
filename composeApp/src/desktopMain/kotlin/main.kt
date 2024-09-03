import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import domain.di.domainModule
import models.viewmodels.di.viewModelModule
import network.di.networkModule
import org.koin.core.context.startKoin
import usecase.di.useCaseModule

fun main(){
    startKoin {
        modules(listOf(viewModelModule, networkModule, useCaseModule, domainModule))
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Flashcards",
        ) {
            App()
        }
    }
}