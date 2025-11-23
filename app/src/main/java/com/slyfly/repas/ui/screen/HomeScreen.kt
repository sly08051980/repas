import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.Color
import com.slyfly.repas.core.datastore.SessionManager

@Composable
fun HomeScreen(sessionManager: SessionManager) {

    // État qui va contenir le token
    var token by remember { mutableStateOf<String?>(null) }

    // Charger le token UNE SEULE FOIS
    LaunchedEffect(Unit) {
        token = sessionManager.getTokenOnce()
    }

    Column {
        Text(text = "home")

        Text(
            text = token ?: "Aucun token",
            color = Color.White
        )
    }
}
