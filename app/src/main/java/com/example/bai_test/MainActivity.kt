package com.example.bai_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.bai_test.ui.theme.Bai_TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bai_TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    AppNavigate(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}


