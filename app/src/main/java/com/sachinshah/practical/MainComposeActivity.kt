package com.sachinshah.practical

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sachinshah.practical.ui.screens.MVIScreen
import com.sachinshah.practical.ui.screens.MVIScreen2
import com.sachinshah.practical.ui.screens.NavHostComposable
import com.sachinshah.practical.ui.screens.ProductListScreen
import com.sachinshah.practical.ui.theme.SachinShahTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SachinShahTheme {
               // NavHostComposable()
                MyNavHost()

                /* Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                 }*/
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SachinShahTheme {
        Greeting("Android")
    }
}

@Composable
fun MyNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController, startDestination = "MVIScreen"
    ) {

        composable(route = "MVIScreen") {
            navController.currentBackStackEntry?.savedStateHandle?.set(
                "fe", "re"
            )
            MVIScreen() {
                navController.navigate("MVIScreen2")
            }
        }
        composable(route = "MVIScreen2") {
            val string = navController.currentBackStackEntry?.savedStateHandle?.get<String>(
                "fe"
            )
            MVIScreen2(string ?: "", {
                navController.popBackStack()
            })
        }

    }


}
