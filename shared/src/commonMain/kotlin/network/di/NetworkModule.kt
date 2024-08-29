package network.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import network.FlashcardDataSource
import network.FlashcardDataSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single { FlashcardDataSourceImpl(client = get()) } bind FlashcardDataSource::class
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
            prettyPrint = true
        }
    }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(get())
            }
            install(HttpCache)
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "http://127.0.0.1:8080"
                    path("/")
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}