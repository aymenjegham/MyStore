
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun MainScreen(navController: NavController) {
    Box() {
        Text(text = "hello main")
    }
}