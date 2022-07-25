package io.appoutlet.flux.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import io.appoutlet.flux.common.core.ui.FluxTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FluxTheme {
                Text("Hello world")
            }
        }
    }
}
