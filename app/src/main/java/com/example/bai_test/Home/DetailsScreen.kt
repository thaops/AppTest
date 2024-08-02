package com.example.bai_test.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bai_test.viewmodel.ProductIDViewModel
import coil.compose.rememberImagePainter
import com.example.bai_test.R
import com.example.bai_test.ui.screen.HomeScreen
import com.example.bai_test.ui.theme.Bai_TestTheme

@Composable
fun DetailsScreen(productId: String, navController: NavHostController) {
    val productViewModel: ProductIDViewModel = viewModel()
    val product by productViewModel.product

    LaunchedEffect(productId) {
        if (productId.isNotEmpty()) {
            productViewModel.fetchProductById(productId)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECFEF2))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
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

            product?.let { prod ->
                Image(
                    painter = rememberImagePainter(prod.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(283.dp)
                        .padding(bottom = 30.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                    Text(
                        text = prod.title,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            textAlign = TextAlign.Justify,
                        ),
                    )
                    }
                    Text(
                        text = "${prod.price} $",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 30.dp),
                        color = Color.Gray
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = prod.description ?: "No details available",
                            fontSize = 16.sp,
                            style = TextStyle(
                                textAlign = TextAlign.Justify,
                                lineHeight = 24.sp // Điều chỉnh lineHeight để tăng khoảng cách giữa các dòng
                            ),
                            modifier = Modifier

                                .align(Alignment.CenterStart) // Căn lề bên trái
                                .fillMaxWidth() // Chiếm toàn bộ chiều r
                        )
                    }

                } ?: Text(text = "Loading...", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = Color(0xFF237F03),
                        shape = RoundedCornerShape(8.dp),
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "BUY", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }


        }
    }
}
