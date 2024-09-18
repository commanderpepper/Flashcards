package util.di

import org.koin.dsl.bind
import org.koin.dsl.module
import util.RandomIdGenerator
import util.RandomIdGeneratorImpl

val utilModule = module {
    single { RandomIdGeneratorImpl() } bind RandomIdGenerator::class
}