package com.example.demoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.LayoutParams
import com.example.demoapplication.databinding.ActivityMainBinding
import com.example.demoapplication.ui.features.listView.ListComposableViewModel
import com.example.demoapplication.ui.features.listView.RemoteResponse
import com.example.demoapplication.ui.theme.DemoApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ListComposableViewModel by viewModels()
    private var recyclerview: RecyclerView? = null
    private val adapter by lazy { ItemsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.items_list)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.adapter = adapter

        viewModel.items.observe(this) {
            adapter.updateItems(it)
        }

        adapter.itemCount
        val message = when (val result = viewModel.remoteResponse) {
            is RemoteResponse.Error -> result.errorMessage
            RemoteResponse.Loading -> "Loading...!!!"
            is RemoteResponse.Success -> result.responseValue
        }
        /*enableEdgeToEdge()
        setContent {
            DemoApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = message,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoApplicationTheme {
        Greeting("Android")
    }
}