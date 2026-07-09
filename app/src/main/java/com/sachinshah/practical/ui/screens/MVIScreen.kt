package com.sachinshah.practical.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sachinshah.practical.mvi.LoginIntent
import com.sachinshah.practical.mvi.viewmodel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlin.math.log

@Composable
fun MVIScreen(onLogin:()-> Unit) {

    val loginViewModel : LoginViewModel = hiltViewModel()
    val state by loginViewModel.state.collectAsStateWithLifecycle()



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {
        TextField(value = state.email, onValueChange = {loginViewModel.processIntents(LoginIntent.EmailChanged(it))},)
        TextField(value = state.password, onValueChange = {loginViewModel.processIntents(LoginIntent.PasswordChange(it))})




        if(state.isLoading){
            CircularProgressIndicator()
        }

        state.isError?.let {
            Text(it)
        }

        state.loginResponse?.let {
            onLogin()
        }


        Button(onClick = { loginViewModel.processIntents(LoginIntent.LoginClicked)}){
            Text("Login")
        }


    }


}