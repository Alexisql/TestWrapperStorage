package com.alexis.testwrapperstorage.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexis.testwrapperstorage.R
import com.alexis.testwrapperstorage.domain.model.User
import com.alexis.testwrapperstorage.ui.core.ShowButton
import com.alexis.testwrapperstorage.ui.core.ShowSpacer
import com.alexis.testwrapperstorage.ui.core.ShowTextField
import com.alexis.wrapperstorage.domain.model.StorageKey

@Composable
fun HomeScreen(modifier: Modifier, viewModel: HomeViewModel) {
    var preferenceString by remember { mutableStateOf("") }
    var preferenceInt by remember { mutableStateOf("") }
    var preferenceUser by remember { mutableStateOf("") }

    val stringKey = StorageKey<String>("String", "HomeScreen", "Alexis")
    val intKey = StorageKey<Int>("Integer", "HomeScreen", "Alexis")
    val userKey = StorageKey<User>("User", "HomeScreen", "Alexis")

    val preferencesState by viewModel.preferences.collectAsStateWithLifecycle()
    viewModel.getPreferencesByScreen("HomeScreen")

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = modifier) {
            ShowSpacer(size = 20)
            ShowTextField(
                text = preferenceString,
                onValueChange = { preferenceString = it }
            )
            ShowSpacer(size = 10)
            ShowButton("Save String") {
                viewModel.saveData(stringKey, preferenceString)
                preferenceString = ""
            }
            ShowSpacer(size = 14)
            ShowTextField(
                text = preferenceInt,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { preferenceInt = it }
            )
            ShowSpacer(size = 10)
            ShowButton("Save Integer") {
                viewModel.saveData(intKey, preferenceInt.toInt())
                preferenceInt = ""
            }
            ShowSpacer(size = 14)
            ShowTextField(
                text = preferenceUser,
                onValueChange = { preferenceUser = it }
            )
            ShowSpacer(size = 10)
            ShowButton("Save Object") {
                viewModel.saveData(
                    userKey,
                    User(name = preferenceUser, age = 30, email = "test@gmail.com")
                )
                preferenceUser = ""
            }
            ShowSpacer(size = 10)
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                ) {
                    item {
                        HeaderTable(modifier = Modifier.padding(vertical = 8.dp))
                        ShowSpacer(5)
                    }
                    items(preferencesState) { (key, value) ->
                        ItemPreference(key, value.toString()) {
                            viewModel.removeData(key)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderTable(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Key",
                modifier = Modifier.weight(0.35f)
            )
            Text(
                text = "Value",
                modifier = Modifier.weight(0.45f)
            )
            Text(
                text = "Action",
                modifier = Modifier.weight(0.20f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemPreference(key: String, preference: String, onDelete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp), // Ajusta el padding
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(0.35f),
                text = key,
            )
            Text(
                modifier = Modifier.weight(0.45f),
                text = preference
            )
            DeletePreference(onDelete = onDelete)
        }
    }
}

@Composable
fun DeletePreference(onDelete: () -> Unit) {
    IconButton(
        onClick = { onDelete() }) {
        Icon(
            modifier = Modifier.padding(2.dp),
            imageVector = Icons.Outlined.Clear,
            contentDescription = stringResource(id = R.string.text_delete),
            tint = Color.Red
        )
    }
}