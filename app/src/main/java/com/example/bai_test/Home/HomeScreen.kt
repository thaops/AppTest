package com.example.bai_test.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bai_test.R
import com.example.bai_test.ui.theme.Bai_TestTheme
import com.example.bai_test.viewmodel.ProductViewModel
import coil.compose.rememberImagePainter

@Composable
fun HomeScreen(navController: NavHostController, productViewModel:ProductViewModel= viewModel()) {

    val products by productViewModel.products
    LaunchedEffect(Unit) {
        productViewModel.fetchProducts()
    }
    products.forEach { product ->
        Log.d("HomeScreen", "Product: ${product.title}, ${product.image}")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECFEF2))
    ) {
        Column(modifier = Modifier.fillMaxSize()
           ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 16.dp)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(color = Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.avatanew),
                        contentDescription = null,
                        modifier = Modifier
                            .width(45.dp)
                            .height(42.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Match your style",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = FontFamily.Serif
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .width(24.dp)
                            .height(24.dp)
                    )
                    Text(
                        text = "Search",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color(0xFFA5A5A5)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center
            ) {
                items(products.size) { index ->
                    val product = products[index]
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(8.dp)
                            .clickable {
                                // Chuyển đến màn hình chi tiết với ID sản phẩm
                                navController.navigate("product_detail/${product.id}")
                            }
                    ) {
                        Image(
                            painter = rememberImagePainter(product.image),
                            contentDescription = null,
                            modifier = Modifier
                                .width(147.dp)
                                .height(207.dp)
                                .clip(RoundedCornerShape(20.dp))

                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = product.title,
                            fontSize = 14.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${product.price} $",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )


                    }
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    Bai_TestTheme {
        HomeScreen(navController)
    }
}
