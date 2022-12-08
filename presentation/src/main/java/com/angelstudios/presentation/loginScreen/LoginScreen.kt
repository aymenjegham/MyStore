import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.angelstudios.presentation.ScreenRoutes
import com.angelstudios.presentation.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Column(modifier = Modifier
                .padding(20.dp)
                .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }

                Text(text = "Login",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(onClick = {
                        navController.navigate(ScreenRoutes.MainScreen.route)
                    },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)) {
                        Text(text = "Login")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                ClickableText(text = AnnotatedString("Forgot password?"),
                    onClick = {
                        navController.navigate(ScreenRoutes.ForgetPassword.route)
                    },
                    style = TextStyle(fontSize = Typography.labelLarge.fontSize,
                        fontFamily = FontFamily.Default,
                        color = MaterialTheme.colorScheme.tertiary))
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                ClickableText("Sign up here") {
                    navController.navigate(ScreenRoutes.SignInScreen.route)
                }
                ClickableText("Skip") {
                    navController.navigate(ScreenRoutes.MainScreen.route)
                }
            }

        }
    }
}

@Composable
private fun ClickableText(text: String, onClick: (Int) -> Unit) {
    ClickableText(text = AnnotatedString(text),
        modifier = Modifier.padding(20.dp),
        onClick = onClick,
        style = TextStyle(fontSize = Typography.labelLarge.fontSize,
            fontFamily = FontFamily.Default,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.tertiary))
}