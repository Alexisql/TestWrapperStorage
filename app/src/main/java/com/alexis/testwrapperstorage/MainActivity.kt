package com.alexis.testwrapperstorage

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.alexis.wrapperstorage.data.local.sharedpreferences.StorageSharedPreferences
import com.alexis.wrapperstorage.presentation.manager.StorageManager
import com.alexis.wrapperstorage.presentation.model.StorageKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storage = StorageManager(StorageSharedPreferences(this@MainActivity, "STORAGE_TEST"))
        val key = StorageKey<String>("Login", "MainActivity", "TestWrapperStorage")
        lifecycleScope.launch {
            storage.put(key, "Hello World")
            val test = storage.get(key, "Default Value")
            Log.e("****Storage****", "User Name: $test")
        }

        enableEdgeToEdge()
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}