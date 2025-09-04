package com.naveen.networkcoroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.naveen.networkcoroutines.ui.components.LoadingDialog
import com.naveen.networkcoroutines.ui.components.ErrorDialog
import com.naveen.networkcoroutines.ui.theme.NetworkCoroutinesTheme
import com.naveen.networkcoroutines.PostViewModel

class PostActivity : ComponentActivity() {
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkCoroutinesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.text.observeAsState("")
                    val loading by viewModel.isLoading.observeAsState(false)
                    val errorMsg by viewModel.error.observeAsState(null)

//                    LaunchedEffect(Unit) {
//                        viewModel.post()
//                    }

//                    rememberCoroutineScope

                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(text = "POST Response:", style = MaterialTheme.typography.titleMedium)
                        Text(text = state)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.post() }) {
                            Text(text = "POST")
                        }
                    }
                    LoadingDialog(show = loading, message = "Fetching data...")
                    ErrorDialog(show = errorMsg != null, message = errorMsg ?: "", onDismiss = { viewModel.clearError() })
                }
            }
        }
    }
}


