import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.deck.DeckScreen
import ui.decklist.DeckListsScreen
import ui.editdeck.EditDeckScreen

enum class FlashcardScreen(val route: String) {
    List("list"),
    Deck("deck"),
    Edit("edit")
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController: NavHostController = rememberNavController()
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = FlashcardScreen.List.route,
        ) {
            composable(route = FlashcardScreen.List.route) {
                DeckListsScreen(modifier = Modifier.fillMaxSize(),
                    onNewDeck = {
                        navController.navigate(route = FlashcardScreen.Edit.route)
                    },
                    onDeckClick = { deckListsItemId ->
                        navController.navigate(route = FlashcardScreen.Deck.route + "?deckId=${deckListsItemId.idValue}")
                    })
            }
            composable(
                route = FlashcardScreen.Deck.route + "?deckId={deckId}",
                arguments = listOf(navArgument("deckId") {
                    type = NavType.StringType
                    nullable = false
                })
            ) {
                DeckScreen(modifier = Modifier.fillMaxSize())
            }
            composable(route = FlashcardScreen.Edit.route + "?deckId={deckId}",
                arguments = listOf(navArgument("deckId"){
                    type = NavType.StringType
                    nullable = true
                })
            ) {
                EditDeckScreen(modifier = Modifier.fillMaxSize(), navController = navController)
            }
        }
    }
}