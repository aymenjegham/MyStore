import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.angelstudios.presentation.R
import com.angelstudios.presentation.ScreenRoutes
import com.angelstudios.presentation.components.CustomTopAppBar
import com.angelstudios.presentation.components.ErrorText
import com.angelstudios.presentation.components.RoundedCornersButton
import com.angelstudios.presentation.signInScreen.RegistrationFormEvent
import com.angelstudios.presentation.signInScreen.SignInViewModel
import com.angelstudios.presentation.signInScreen.ValidationEvent
import com.angelstudios.presentation.utils.LockScreenOrientation


@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(navController: NavController, viewModel: SignInViewModel) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


    val state = viewModel.registrationScreenUiState

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                ValidationEvent.Success -> {
                    navController.navigate(ScreenRoutes.MainScreen.route) {
                        popUpTo(0)
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(navController, stringResource(R.string.sign_up), true)
        },
        content = {
            Box(
                Modifier.fillMaxSize(),
                 contentAlignment  = Alignment.Center
            ) {
                Column(
                    Modifier
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(text = stringResource(R.string.sign_up),
                        style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        singleLine = true,
                        label = { Text(text = stringResource(R.string.email)) },
                        value = state.email,
                        onValueChange = { email ->
                            viewModel.onEvent(RegistrationFormEvent.EmailChanged(email))
                        },
                        isError = state.emailError != null,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    if (state.emailError != null) {
                        ErrorText(stringSource = state.emailError)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        singleLine = true,
                        label = { Text(text = stringResource(R.string.password)) },
                        value = state.password,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = {
                            viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
                        },
                        isError = state.passwordError != null,
                    )

                    if (state.passwordError != null) {
                        ErrorText(stringSource = state.passwordError)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        singleLine = true,
                        label = { Text(text = stringResource(R.string.confirm_password)) },
                        value = state.repeatedPassword,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = {
                            viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                        },
                        isError = state.repeatedPasswordError != null,
                    )

                    if (state.repeatedPasswordError != null) {
                        ErrorText(stringSource = state.repeatedPasswordError)
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    RoundedCornersButton(stringResource(R.string.sign_up)) {
                        viewModel.onEvent(RegistrationFormEvent.Submit)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (state.apiError != null) {
                        ErrorText(stringSource = state.apiError)
                    }

                }
                   if(state.showLoader) CircularProgressIndicator()

            }
        }
    )
}


