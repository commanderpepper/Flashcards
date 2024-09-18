import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import domain.di.domainModule
import models.viewmodels.di.viewModelModule
import network.di.networkModule
import org.koin.core.context.startKoin
import ui.theme.AppTypography
import ui.theme.lightScheme
import usecase.di.useCaseModule
import util.di.utilModule

fun main(){
    startKoin {
        modules(listOf(viewModelModule, networkModule, useCaseModule, domainModule, utilModule))
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Flashcards",
        ) {
            MaterialTheme(
                colorScheme = lightScheme,
                typography = AppTypography
            ){
                App()
            }
        }
    }
}