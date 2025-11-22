package com.slyfly.repas.ui.screen

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.slyfly.repas.logic.state.SignUpUiState
import com.slyfly.repas.logic.interfaces.SignUpIntent
import com.slyfly.repas.logic.viewmodel.SignUpViewModel
import com.slyfly.repas.ui.components.SignUpField
import com.slyfly.repas.ui.theme.dancingScript
import org.koin.androidx.compose.koinViewModel

// ---------- ROUTE (avec ViewModel + Koin) ----------
@Composable
fun SignUpScreen(
    onRegistered: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel()
) {
    val state by viewModel.observeUiState().collectAsState()

    // navigation si success
    LaunchedEffect(state.success) {
        if (state.success) onRegistered()
    }

    SignUpContent(
        state = state,
        onFirstName = { viewModel.onIntent(SignUpIntent.ChangeFirstName(it)) },
        onLastName = { viewModel.onIntent(SignUpIntent.ChangeLastName(it)) },
        onCity = { viewModel.onIntent(SignUpIntent.ChangeCity(it)) },
        onPostalCode = { viewModel.onIntent(SignUpIntent.ChangePostalCode(it)) },
        onEmail = { viewModel.onIntent(SignUpIntent.ChangeEmail(it)) },
        onPassword = { viewModel.onIntent(SignUpIntent.ChangePassword(it)) },
        onConfirmPassword = { viewModel.onIntent(SignUpIntent.ChangeConfirmPassword(it)) },
        onSubmit = { viewModel.onIntent(SignUpIntent.Submit) }
    )
}
@Composable
fun SignUpContent(
    state: SignUpUiState,
    onFirstName: (String) -> Unit,
    onLastName: (String) -> Unit,
    onCity: (String) -> Unit,
    onPostalCode: (String) -> Unit,
    onEmail: (String) -> Unit,
    onPassword: (String) -> Unit,
    onConfirmPassword: (String) -> Unit,
    onSubmit: () -> Unit
) {
    // ⭐ Liste pour alléger la vue
    val fields = listOf(
        SignUpField("Prénom", state.firstName, onFirstName),
        SignUpField("Nom", state.lastName, onLastName),
        SignUpField("Ville", state.city, onCity),
        SignUpField("Code Postal", state.postalCode, onPostalCode),
        SignUpField("Email", state.email, onEmail),
        SignUpField("Mot de passe", state.password, onPassword, isPassword = true),
        SignUpField("Confirmer le mot de passe", state.confirmPassword, onConfirmPassword, isPassword = true)
    )

    Column(Modifier.padding(16.dp).
    verticalScroll(rememberScrollState())
        .imePadding()) {
        Text(
            text = "Bienvenue sur\nNutrition",
            textAlign = TextAlign.Center,
            fontSize = 70.sp,
            lineHeight = 62.sp,
            fontFamily = dancingScript,
            fontWeight = FontWeight.Normal,

            )
        fields.forEach { field ->
            OutlinedTextField(
                value = field.value,
                onValueChange = field.onChange,
                label = { Text(field.label, fontFamily = dancingScript) },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                visualTransformation = if (field.isPassword)
                    PasswordVisualTransformation() else VisualTransformation.None,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
        }

        Button(
            onClick = onSubmit,
            enabled = !state.isLoading
        ) {
            if (state.isLoading)
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp
                )
            else Text("Créer mon compte")
        }

        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
