import androidx.compose.ui.window.ComposeUIViewController
import shared.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }