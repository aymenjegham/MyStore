import android.content.pm.ActivityInfo
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.angelstudios.presentation.R
import com.angelstudios.presentation.ScreenRoutes
import com.angelstudios.presentation.components.ErrorText
import com.angelstudios.presentation.components.RoundedCornersButton
import com.angelstudios.presentation.loginScreen.LoginScreenEvent
import com.angelstudios.presentation.loginScreen.LoginViewModel
import com.angelstudios.presentation.loginScreen.NavigationEvent
import com.angelstudios.presentation.signInScreen.ValidationEvent
import com.angelstudios.presentation.ui.theme.Typography
import com.angelstudios.presentation.utils.LockScreenOrientation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


    val state = viewModel.loginUiState

    val context = LocalContext.current
    LaunchedEffect(key1 = context ){
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

    LaunchedEffect(key1 = context) {
        viewModel.navigationEventChannel.collect { event ->
            when (event) {
                NavigationEvent.Skip -> {
                    navController.navigate(ScreenRoutes.MainScreen.route) {
                        popUpTo(0)
                    }
                }
                NavigationEvent.ToForgetPasswordScreen -> {
                    navController.navigate(ScreenRoutes.ForgetPassword.route)
                }
                NavigationEvent.ToMain -> {
                    navController.navigate(ScreenRoutes.MainScreen.route) {
                        popUpTo(0)
                    }
                }
                NavigationEvent.ToSignInScreen -> {
                    navController.navigate(ScreenRoutes.SignInScreen.route)
                }
            }
        }
    }

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                Text(text = stringResource(R.string.login),
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    singleLine = true,
                    label = { Text(text = stringResource(R.string.email)) },
                    value = state.email,
                    onValueChange = { email ->
                        viewModel.onEvent(LoginScreenEvent.EmailChanged(email))
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
                    onValueChange = { password ->
                        viewModel.onEvent(LoginScreenEvent.PasswordChanged(password))
                    },
                    isError = state.passwordError != null,
                )

                if (state.passwordError != null) {
                    ErrorText(stringSource = state.passwordError)
                }

                Spacer(modifier = Modifier.height(16.dp))
                RoundedCornersButton(stringResource(R.string.login)) {
                    viewModel.onEvent(LoginScreenEvent.Submit)
                }

                Spacer(modifier = Modifier.height(16.dp))
                ClickableText(text = AnnotatedString(stringResource(R.string.forgot_password)),
                    onClick = {
                        viewModel.onEvent(LoginScreenEvent.GoToForgetPasswordScreen)
                    },
                    style = TextStyle(fontSize = Typography.labelLarge.fontSize,
                        fontFamily = FontFamily.Default,
                        color = MaterialTheme.colorScheme.tertiary))
                Spacer(modifier = Modifier.height(16.dp))

                if (state.apiError != null) {
                    ErrorText(stringSource = state.apiError)
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                ClickableLabelText(stringResource(R.string.sign_up_here)) {
                    viewModel.onEvent(LoginScreenEvent.GoToSignInScreen)

                }
                ClickableLabelText(stringResource(R.string.skip)) {
                    viewModel.onEvent(LoginScreenEvent.Skip)
                }
            }
            if (state.showLoader) CircularProgressIndicator()


        }
    }
}


@Composable
private fun ClickableLabelText(text: String, onClick: (Int) -> Unit) {
    ClickableText(text = AnnotatedString(text),
        modifier = Modifier.padding(20.dp),
        onClick = onClick,
        style = TextStyle(fontSize = Typography.labelLarge.fontSize,
            fontFamily = FontFamily.Default,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.tertiary))
}