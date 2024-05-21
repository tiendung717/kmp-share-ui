package com.solid.kmp

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EdgeToEdgeContent {
                App()
            }
        }
    }
}

@Composable
fun EdgeToEdgeContent(content: @Composable () -> Unit) {
    Box(modifier = Modifier.systemBarsPadding()) {
        content()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}