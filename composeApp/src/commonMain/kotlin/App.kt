import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chipmango.kmp.core.navigation.Navigation
import com.chipmango.kmp.core.navigation.destination.destination
import com.chipmango.kmp.core.theme.AppTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import shared.ui.Screens
import shared.ui.home.HomeScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    KoinContext {
        AppTheme(
            useDarkTheme = isSystemInDarkTheme(),
            themeColors = koinInject(),
        ) {
            val navController = rememberNavController()
            Navigation(
                navController = navController,
                startingDestination = Screens.Home
            ) {
                destination(Screens.Home) {
                    HomeScreen()
                }
            }
        }
    }
}