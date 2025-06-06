package com.example.demoapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.demoapplication.ui.theme.DemoApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstActivity : ComponentActivity() {
    val viewModel: FirstSecondViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.collectFlow()

        Log.d("TAG", "onCreate: ${this::class.java}")

        lifecycleScope.launch {
//            viewModel.shouldNavigateState.collect {
//                Log.d("TAG", "shouldNavigate: $it")
//                if (it) {
//                    startActivity(Intent(this@MainActivity, SecondActivity::class.java))
//                }
//            }
        }

        viewModel.shouldNavigateLive.observe(this) {
            Log.d("TAG", "shouldNavigate: $it")
            if (it) {
                startActivity(Intent(this@FirstActivity, SecondActivity::class.java))
            }
        }

        enableEdgeToEdge()
        setContent {
            DemoApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "First Activity",
                        modifier = Modifier.padding(innerPadding),
                        buttonClick = {
                            viewModel.buttonClick()
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart: ${this::class.java}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: ${this::class.java}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: ${this::class.java}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: ${this::class.java}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy: ${this::class.java}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "onRestart: ${this::class.java}")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, buttonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $name!",
            color = Color.Red,
            modifier = modifier.padding(bottom = 30.dp)
        )

        Button(
            onClick = buttonClick,
        ) {
            Text(
                text = "Click Me!!",
                color = Color.Red,
                modifier = modifier.padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoApplicationTheme {
        Greeting(
            "Android",
            buttonClick = {}
        )
    }
}