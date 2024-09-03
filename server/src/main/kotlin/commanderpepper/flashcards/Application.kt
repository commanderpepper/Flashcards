package commanderpepper.flashcards

import Greeting
import SERVER_PORT
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import models.data.DeckNetwork

private val deckSource = DeckSourceImpl()

fun main() {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)
    scope.launch {
        deckSource.insertDeck(DeckNetwork("1", "Test", emptyList()))
    }
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation){
        json(
            Json {
                prettyPrint = true
                isLenient = true
            }
        )
    }
    routing {

        staticResources("static", "static")

        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        get("/decks"){
            call.respond(deckSource.getAllDecks())
        }

        get("/deck/{deckId}") {
            val deckId = call.parameters["deckId"]
            if (deckId != null){
                val deck = deckSource.getDeck(deckId)
                if(deck != null){
                    call.respond(deck)
                }
                else {
                    call.respond(status = HttpStatusCode.NotFound, "Deck not found")
                }
            }
            else {
                call.respond(HttpStatusCode.BadRequest, "Deck Id is invalid")
            }
        }

        post("/deck") {
            try {
                val deck = call.receive<DeckNetwork>()
                deckSource.insertDeck(deck)
                call.respond(HttpStatusCode.NoContent)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}