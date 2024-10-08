package ui.editdeck

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.ui.editdeck.EditDeckFlashCardItem

@Composable
fun EditDeckFlashCardItemUI(
    editDeckFlashCardItem: EditDeckFlashCardItem,
    onFlashcardSaveChanges: (EditDeckFlashCardItem) -> Unit,
    markFlashCardForDeletion: (EditDeckFlashCardItem) -> Unit
) {
    var front by remember { mutableStateOf(editDeckFlashCardItem.front) }
    var back by remember { mutableStateOf(editDeckFlashCardItem.back) }

    Card(modifier = Modifier.padding(4.dp).fillMaxWidth().requiredHeight(128.dp)) {
        Column(modifier = Modifier.padding(4.dp)) {
            // Row of buttons for a flashcard, Save Changes, Mark for Deletion
            if(editDeckFlashCardItem.markedForDeletion){
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text("Marked for deletion")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = {
                    onFlashcardSaveChanges(editDeckFlashCardItem.copy(front = front, back = back))
                }){
                    Text("Save Changes")
                }
                Button(
                    onClick = {
                        markFlashCardForDeletion(editDeckFlashCardItem.copy(markedForDeletion = editDeckFlashCardItem.markedForDeletion.not()))
                    },
                    border = if (editDeckFlashCardItem.markedForDeletion) BorderStroke(2.dp, Color.Red) else null
                ) {
                    Text("Delete Card")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedTextField(modifier = Modifier.weight(.5f).padding(4.dp), value = front, onValueChange = { front = it }, label = { Text("Front") })
                OutlinedTextField(modifier = Modifier.weight(.5f).padding(4.dp), value = back, onValueChange = { back = it }, label = { Text("Back") })
            }
        }
    }
}