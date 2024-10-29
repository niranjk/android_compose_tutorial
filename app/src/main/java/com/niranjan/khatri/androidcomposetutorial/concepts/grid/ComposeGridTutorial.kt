package com.niranjan.khatri.androidcomposetutorial.concepts.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageGrid() {
    val imageUrls = remember {
        listOf(
            "https://picsum.photos/200/300",
            "https://picsum.photos/300/300",
            "https://picsum.photos/400/300",
            "https://picsum.photos/500/300",
            "https://picsum.photos/600/300",
            "https://picsum.photos/700/300",
            // ... more image URLs
        )
    }

    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(imageUrls) { imageUrl ->
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Grid Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
fun LazyVerticalGrid_Preview(){
    ImageGrid()
}


@Composable
fun ProductGrid() {
    val products = remember {
        listOf(
            Product("Product 1", "$10"),
            Product("Product 2", "$20"),
            Product("Product 3", "$30"),
            // ... more products
        )
    }

    LazyHorizontalGrid(
        modifier = Modifier.padding(16.dp),
        rows = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .clickable { /* Handle product click */ },
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = product.name)
                    Text(text = product.price)
                }
            }
        }
    }
}

data class Product(val name: String, val price: String)


@Preview
@Composable
fun LazyHorizontalGrid_Preview(){
    ProductGrid()
}


@Composable
fun GridWithSpanningHeader() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) { // Spanning header
            Text(
                text = "This is a spanning header",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            )
        }
        items(10) { index ->
            Card(
                modifier = Modifier.height(100.dp).padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = "Item $index",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun GridWithSpanningHeaderPreview(){
    GridWithSpanningHeader()
}