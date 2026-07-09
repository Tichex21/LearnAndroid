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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sachinshah.practical.mvi.LoginIntent
import com.sachinshah.practical.mvi.viewmodel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlin.math.log

@Composable
fun MVIScreen2(text:String,onBack:()-> Unit) {

    val prodstt= produceState(0) {
        for (i in 0..10){
            delay(1000)
            value =i
        }
    }

    val ste1= remember{ mutableStateOf(1) }
    val ste2= remember{ mutableStateOf(2) }
    val dertr= remember {
        derivedStateOf {
            ste1.value + ste2.value
        }
    }

    LaunchedEffect(Unit) { }

    DisposableEffect(Unit) {

        onDispose {

        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {

        Text(text)
        Button(onClick = {onBack()}) {
            Text("Back ${prodstt.value}")
        }

    }


}