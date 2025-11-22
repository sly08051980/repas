package com.slyfly.repas.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slyfly.repas.logic.interfaces.SignInItent
import com.slyfly.repas.logic.state.SignInUiState
import com.slyfly.repas.logic.viewmodel.SignInViewModel
import com.slyfly.repas.ui.components.SignInField
import com.slyfly.repas.ui.nav.Routes
import com.slyfly.repas.ui.theme.dancingScript
import org.koin.androidx.compose.koinViewModel

@Composable

fun SignInScreen(
    onValidate:() -> Unit,
    onGoToSignUp:() -> Unit,
    viewModel: SignInViewModel= koinViewModel()
) {
    val state by viewModel.observeUiState().collectAsState()

    LaunchedEffect (state.success){
        if (state.success) onValidate()
    }

    SignInContent(
        state = state,
        onEmail={viewModel.onIntent(SignInItent.ChangeEmail(it))},
        onPassword={viewModel.onIntent(SignInItent.ChangePassword(it))},
        onSubmit={viewModel.onIntent(SignInItent.Submit)},
        onGoToSignUp = onGoToSignUp
    )
}
@Composable
fun SignInContent(
    state:SignInUiState,
    onEmail: (String) ->Unit,
    onPassword: (String) -> Unit,
onSubmit:()->Unit,
    onGoToSignUp: () -> Unit
){
    val fields = listOf(
        SignInField("Email",state.email,onEmail),
        SignInField("Mot de Passe",state.password,onPassword, isPassword = true)
    )
    Column (Modifier.padding(16.dp).imePadding()){
        Text(
            text = "Bienvenue sur\nNutrition",
            textAlign = TextAlign.Center,
            fontSize = 70.sp,
            lineHeight = 62.sp,
            fontFamily = dancingScript,
            fontWeight = FontWeight.Normal,

            )
        fields.forEach{field->
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
        Row {
            Button(
                onClick = onSubmit,
                enabled = !state.isLoading
            ) {
                if (state.isLoading)
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                else Text("Connexion")
            }
            state.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }

        }
        Row(modifier=Modifier.fillMaxWidth()
            .padding(0.dp,30.dp,0.dp,0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){

            Text(text="Pas encore inscrit cliquez ici",
                fontFamily = dancingScript,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier=Modifier.clickable {onGoToSignUp() }
            )
        }
    }
}
