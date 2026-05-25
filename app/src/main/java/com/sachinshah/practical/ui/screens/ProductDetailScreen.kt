package com.sachinshah.practical.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sachinshah.practical.R
import com.sachinshah.practical.model.ProductModel
import com.sachinshah.practical.ui.theme.ColorBlack
import com.sachinshah.practical.ui.theme.ColorWhite
import kotlin.math.roundToInt


//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductDetail(productModel: ProductModel,onBackPress : ()-> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    )
    {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .weight(0.9f)
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())


            )

            {

                Box()
                {


                    AsyncImage(
                        model = productModel.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f / 1.2f),

                        contentScale = ContentScale.Crop
                    )




                    Image(

                        painter = painterResource(R.drawable.heart),
                        colorFilter = ColorFilter.tint(color = Color.Red),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(
                                x = (-16).dp,
                                y = 20.dp
                            )
                            .shadow(
                                elevation = 10.dp,
                                shape = CircleShape
                            )
                            .clip(CircleShape)
                            .background(ColorWhite)
                            .padding(10.dp)
                            .size(20.dp)

                    )


                }

                Text(
                    text = productModel.title,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 30.dp),
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {

                    Image(
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(6.dp)
                    )

                    Text(
                        text = "4.5 (355 Reviews)",
                        color = Color(0xFF606060),
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontSize = 12.sp,

                        )
                }
                Text(
                    text = productModel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp),
                ) {


                    Text(
                        text = "Read More...", style = MaterialTheme.typography.bodyMedium,
                        color = Color.Blue,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold))
                    )
                    Image(
                        painter = painterResource(R.drawable.down),
                        colorFilter = ColorFilter.tint(color = Color.Blue),
                        contentDescription = null,

                        )

                }

                Text(
                    text = "Facilities",
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 20.dp),
                )


                LazyRow(

                    content = {
                    items(items = fakeList(), itemContent = {
                        FacilityRow()
                    })
                })
            }
            Image(
                painter = painterResource(R.drawable.outline_arrow_back_ios_24),
                colorFilter = ColorFilter.tint(color = ColorBlack.copy(alpha = 0.3f)),
                contentDescription = null,
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .clickable{
                        onBackPress()
                    }
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(size = 10.dp))
                    .background(
                        color = ColorWhite.copy(
                            alpha = 0.95f
                        )
                    )
                    .padding(start = 15.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                    .align(alignment = Alignment.TopStart).size(18.dp)
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .weight(0.1f)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(0.3f)
            ) {

                Text(
                    text = "Price",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                    fontSize = 12.sp,
                )
                Text(
                    text = "$${productModel.price.roundToInt()}",
                    color = Color(0xFF2DD7A4),
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontSize = 24.sp
                )

            }

            ElevatedButton(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                shape = RoundedCornerShape(size = 15.dp),
                elevation =
                    ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 14.dp
                    ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f)

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Book Now")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Image(
                        painter = painterResource(R.drawable.arrow___right),
                        contentDescription = null, modifier = Modifier.size(20.dp)
                    )
                }
            }


        }
    }

}

fun fakeList() = arrayListOf("", "", "", "", "", "", "", "", "", "", "", "")

@Preview(showBackground = true)
@Composable
fun FacilityRow() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Gray.copy(alpha = 0.05f))
            .padding(15.dp)

    ) {
        Image(
            painter = painterResource(R.drawable.outline_circles_ext_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.Gray)
        )
        Text(
            "1 Heater", style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }

}
