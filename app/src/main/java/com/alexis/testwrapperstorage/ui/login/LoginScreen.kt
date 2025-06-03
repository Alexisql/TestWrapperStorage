package com.alexis.testwrapperstorage.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexis.testwrapperstorage.R
import com.alexis.testwrapperstorage.ui.core.ShowButton
import com.alexis.testwrapperstorage.ui.core.ShowSpacer
import com.alexis.testwrapperstorage.ui.core.ShowTextField
import com.alexis.wrapperstorage.domain.model.StorageKey

@Composable
fun LoginScreen(modifier: Modifier, viewModel: LoginViewModel) {
    var login by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = modifier) {
            Image(
                painter = painterResource(id = R.drawable.meli),
                contentDescription = stringResource(id = R.string.description_logo),
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            )
            ShowSpacer(size = 100)
            ShowTextField(
                text = login,
                onValueChange = { login = it }
            )
            ShowSpacer(size = 10)
            ShowButton("Login") {
                val userKey = StorageKey<String>("Login", "LoginScreen", "Alexis")
                viewModel.saveData(userKey, login)
            }
            ShowSpacer(size = 14)
        }

    }
}