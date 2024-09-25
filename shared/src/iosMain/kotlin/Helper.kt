import domain.di.domainModule
import models.viewmodels.di.viewModelModule
import network.di.networkModule
import org.koin.core.context.startKoin
import usecase.di.useCaseModule
import util.di.utilModule

fun initKoin(){
    startKoin {
        modules(listOf(viewModelModule, networkModule, useCaseModule, domainModule, utilModule))
    }
}