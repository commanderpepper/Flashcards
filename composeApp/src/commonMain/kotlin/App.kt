import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.decklist.DeckListsScreenState

enum class FlashcardScreen(val title: String){
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
            startDestination = FlashcardScreen.List.title,
        ) {
            composable(route = FlashcardScreen.List.title) {
                DeckListsScreenState(modifier = Modifier.fillMaxSize()){

                }
            }
            composable(route = FlashcardScreen.Deck.title) {

            }
            composable(route = FlashcardScreen.Edit.title) {

            }
        }
    }
}