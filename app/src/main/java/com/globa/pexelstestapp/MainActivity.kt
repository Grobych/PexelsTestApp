package com.globa.pexelstestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.globa.pexelstestapp.ui.theme.PexelsTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PexelsTestAppTheme {

            }
        }
    }
}