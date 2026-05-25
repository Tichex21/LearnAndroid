package com.sachinshah.practical.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sachinshah.practical.model.ProductModel

enum class AppScreens {

    PRODUCT_LIST_SCREE,
    PRODUCT_DETAIL_SCREE,


}


@Composable
fun NavHostComposable() {

    val navController = rememberNavController()
    NavHost(
        navController, startDestination = AppScreens.PRODUCT_LIST_SCREE.name
    ) {

        composable(
            route = AppScreens.PRODUCT_LIST_SCREE.name
        ) {


            ProductListScreen {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "productModel", it
                )
                navController.navigate(AppScreens.PRODUCT_DETAIL_SCREE.name)
            }
        }
        composable(
            route = AppScreens.PRODUCT_DETAIL_SCREE.name){ backstackEntry->

            val productModel =
            navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<ProductModel>(
                    "productModel"
                )

            productModel?.let {

                ProductDetail(
                    productModel = it
                )
                {
                    navController.popBackStack()
                }

            }


        }

    }

}