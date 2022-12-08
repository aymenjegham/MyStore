
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.angelstudios.presentation.components.CustomTopAppBar



@Composable
fun SignInScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Signup", true)
        }, content = {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign up",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

        })
}