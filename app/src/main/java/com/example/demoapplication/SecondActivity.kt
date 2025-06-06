package com.example.demoapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.demoapplication.ui.theme.DemoApplicationTheme
import com.example.myapplication.AggregatedUserDataUsecase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondActivity : ComponentActivity() {
//    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userCase = AggregatedUserDataUsecase()
        lifecycleScope.launch {
            val data = userCase.getAggregatedUserData()
            Log.w("222", "getAggregatedUserData: $data")
            Log.w("222", "getAggregatedUserData: $data")
            Log.w("222", "getAggregatedUserData: $data")
        }
        Log.d("TAG", "onCreate: ${this::class.java}")
        setContent {
            SecondUI()
        }
    }

    @Composable
    fun SecondUI() {
//        val numbers  = viewModel.numbers.collectAsState()
//        LazyColumn {
//            items(numbers.value.size) {
                Text(
                    text = "numbers.value[it].toString()",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                )

                HorizontalDivider()
//            }
//        }
    }

    @Composable
    @Preview
    fun Preview_secondActivity() {
        DemoApplicationTheme {
            SecondUI()
        }
    }
}