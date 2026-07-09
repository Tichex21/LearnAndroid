package com.sachinshah.practical.ui.screens

import android.graphics.drawable.shapes.RoundRectShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.sachinshah.practical.model.ProductModel
import com.sachinshah.practical.ui.theme.ColorBlack
import com.sachinshah.practical.ui.theme.ColorTransparentBlack
import com.sachinshah.practical.ui.theme.ColorWhite
import com.sachinshah.practical.viewmodels.ProductViewModel
import kotlin.math.roundToInt


//@Preview
@Composable
fun ProductListScreen(onItemClick: (productModel: ProductModel) -> Unit) {
    if (LocalInspectionMode.current) {
        // Preview UI
       // Text("Preview Mode")
        return
    }
    val productViewModel: ProductViewModel   = hiltViewModel()
    val products = productViewModel.products.collectAsStateWithLifecycle()

    if(products.value.isEmpty()){

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(


                modifier = Modifier.

                clip(RoundedCornerShape(corner = CornerSize(50.dp)))
            )
        }


    }else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),

            verticalArrangement =
                Arrangement.spacedBy(10.dp),

            horizontalArrangement =
                Arrangement.spacedBy(10.dp),

            contentPadding =
                PaddingValues(10.dp),

            modifier = Modifier
                .fillMaxSize()


        )
        {

            items(
                items = products.value,
                key = { it.id }
            ) { product ->

                ProductItem(
                    product,
                    onItemClick
                )
            }
        }
    }



}


//@Preview(showBackground = true)
@Composable
fun ProductItem(productModel: ProductModel, onItemClick: (productModel: ProductModel) -> Unit) {

    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, ColorTransparentBlack),
        modifier = Modifier
            .clickable {
                onItemClick(productModel)
            }
            .fillMaxWidth()
    ) {
        Box {

            AsyncImage(
                model = productModel.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1.2f),

                contentScale = ContentScale.Crop
            )/*AsyncImage(
                model = R.drawable.bg_black_transparent,
                contentDescription = "product image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )*/
            Text(
                text = productModel.title.take(10),
                color = ColorWhite,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(5.dp)
                    .background(
                        color = ColorBlack.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp)
                    )
                    .padding(
                        horizontal = 10.dp, vertical = 5.dp
                    )
            )

            Text(
                text = "$${productModel.price.roundToInt()}",
                color = ColorWhite,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.dp)
                    .background(
                        color = ColorBlack.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp)
                    )
                    .padding(
                        horizontal = 10.dp, vertical = 5.dp
                    )
            )
        }

    }

}