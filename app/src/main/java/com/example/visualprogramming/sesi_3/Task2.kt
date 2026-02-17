package com.example.visualprogramming.sesi_3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Purple = Color(0xff5a00ff)
private val BackgroundLight = Color(0xfff5f5f5)
private val CardPlaceHoler = Color(0xffe5e5e5)

data class CatalogItem(
    val id: Int,
    val name: String,
    val price: String
)

@Preview
@Composable
fun ShopCatalogScreenPreview() {
    ShopCatalogScreen()
}

@Composable
fun ShopCatalogScreen() {

    val categories = listOf("All", "Popular", "Promo", "Fruit", "Drink")
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }
    val items = remember {
        List(6) {
            CatalogItem(
                id = it,
                name = "nama item",
                price = "Rp. 12.000"
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(Color.Black)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Purple),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Shop Catalog",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .background(BackgroundLight)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach {
                    category ->
                    val isSelected = category == selectedCategory
                    Surface(
                        shape = MaterialTheme.shapes.large,
                        color = if (isSelected) Purple else Color(0xffe8e0f5),
                        tonalElevation = 0.dp,
                        shadowElevation = 0.dp,
                        modifier = Modifier.height(32.dp)
                            .clickable {
                                selectedCategory = category
                            }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 14.dp)
                        ) {
                            Text(
                                text = category,
                                fontSize = 13.sp,
                                color = if (isSelected) Color.White else
                                    Color(0xff5b5b5b)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it},
                placeholder = {
                    Text(
                        text = "Search... "
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items) {
                    item -> CatalogCard(item = item)
                }
            }
        }
        BottomBar(totalText = "Total: Rp:24.000")
    }
}

@Composable
fun BottomBar(totalText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Purple),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = totalText,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Purple
                ),
                modifier = Modifier.height(40.dp).width(120.dp)
            ) {
                Text(
                    text = "Checkout",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun CatalogCard(item: CatalogItem) {
    Surface(
        color = Color.White,
        shadowElevation = 6.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(CardPlaceHoler)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = item.price,
                fontSize = 13.sp,
                color = Color(0xff555555)
            )
            Spacer(
                modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width((70.dp))
                ) {
                    Text(
                        text = "Add",
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
