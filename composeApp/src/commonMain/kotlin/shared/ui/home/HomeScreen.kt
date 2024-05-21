package shared.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import shared.di.koinViewModel
import shared.theme.themeColors
import shared.viewmodel.DummyViewModel

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Home") })
        }
    ) {
        val dummyViewModel = koinViewModel<DummyViewModel>()
        val timer by dummyViewModel.timer.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize()
                .background(
                    color = themeColors().background.Normal
                )
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = timer.toString(),
                color = themeColors().project.Normal,
                style = UIKitTypography.Title1Bold24
            )
        }
    }
}