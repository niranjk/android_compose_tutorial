package com.niranjan.khatri.androidcomposetutorial.bootcamp

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AndroidComposeTutorialTheme
import com.niranjan.khatri.androidcomposetutorial.ui.theme.BootcampTheme

/**
 * @author NIRANJAN KHATRI
 * @since 05/05/24
 * @version 1
 */

/*
@Preview(
    showBackground = true,
    heightDp = 50,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "SearchScreenPreviewDark"
)
@Preview(showBackground = true, name = "SearchScreenPreviewLight", heightDp = 50)
 */
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
                      Icon(imageVector = Icons.Default.Search,
                          contentDescription = null)

        },
        placeholder = {
                      Text(text = stringResource(id = R.string.label_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(50.dp)
    )
}


/*
@Preview(
    showBackground = true,
    heightDp = 120,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 120)
 */
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.ic_car,
    @StringRes name: Int = R.string.label_car){
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Text( text = stringResource(name),
            color =  MaterialTheme.colorScheme.primary, 
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}



@Preview(
    showBackground = true, heightDp = 120, uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 120)
@Composable
fun FavoriteProductCard(
    modifier: Modifier = Modifier,
    @DrawableRes drawable: Int = R.drawable.ic_car,
    @StringRes text: Int = R.string.label_car_desc,

) {
    BootcampTheme {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(300.dp)
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                )
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    }
}

data class Product(
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
)

/*
@Preview(
    showBackground = true, heightDp = 300, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 300)

 */
@Composable
fun ProductRowItemScreen(
    modifier: Modifier = Modifier
) {
    val alignYourBodyData = listOf(
        Product(R.drawable.ic_car, R.string.label_car_prosche),
        Product(R.drawable.ic_car, R.string.label_car_audi),
        Product(R.drawable.ic_car, R.string.label_car_bugatti),
        Product(R.drawable.ic_car, R.string.label_car_ferrari),
        Product(R.drawable.ic_car, R.string.label_car_lamborghini),
    )
    BootcampTheme {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(alignYourBodyData) { item ->
                ProductItem(icon = item.icon, name = item.name)
            }
        }
    }
}

/*
@Preview(
    showBackground = true, heightDp = 300, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 300)
 */
@Composable
fun FavoriteProductsScreen(
    modifier: Modifier = Modifier
) {
    val favoriteProductsList = listOf(
        Product(R.drawable.ic_car, R.string.label_car_prosche),
        Product(R.drawable.ic_car, R.string.label_car_audi),
        Product(R.drawable.ic_car, R.string.label_car_bugatti),
        Product(R.drawable.ic_car, R.string.label_car_ferrari),
        Product(R.drawable.ic_car, R.string.label_car_lamborghini),
    )
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(150.dp)
    ) {
        items(favoriteProductsList) { item ->
            FavoriteProductCard(modifier = Modifier.height(60.dp), drawable = item.icon, text = item.name)
        }
    }
}

// SLOT APIs
@Composable
fun ScreenContentSlot(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(  // Title
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 20.dp, bottom = 8.dp)
                .padding(horizontal = 8.dp)
        )
        content() // Your Slot Content that you will pass on each call
    }
}

/*
@Preview(
    showBackground = true, heightDp = 400, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 400)
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // When you have limited number of elements you can simply add Column to avoid slowing down app
    // and add the scroll behaviour manually
    Column(
        modifier
            .verticalScroll(rememberScrollState()) // <--- added manually persistent scroll behaviour
    ) {
        Spacer(Modifier.height(16.dp))
        SearchScreen(Modifier.padding(horizontal = 8.dp))
        ScreenContentSlot(title = R.string.label_product_list) {
            ProductRowItemScreen()
        }
        ScreenContentSlot(title = R.string.label_favorite_list) {
            FavoriteProductsScreen()
        }
        Spacer(Modifier.height(8.dp))
    }
}

/*
@Preview(
    showBackground = true, heightDp = 100, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 100)

 */
@Composable
private fun BottomNavigationBarScreen(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier.height(80.dp)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.label_home))
            },
            selected = true,
            onClick = {
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.label_profile))
            },
            selected = false,
            onClick = {
            }
        )
    }
}

@Composable
fun MyBootcampTwoApp(windowScreenSize: WindowSizeClass){
    when (windowScreenSize.widthSizeClass){
        WindowWidthSizeClass.Compact -> MyBootcampTwoApp_Portrait()
        WindowWidthSizeClass.Expanded -> MyBootcampTwoApp_Landscape()
    }
}

@Composable
fun MyBootcampTwoApp_Portrait() {
    AndroidComposeTutorialTheme {
        Scaffold(
            bottomBar = { BottomNavigationBarScreen() }
        ) { padding ->
            MainScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun MyBootcampTwoApp_Landscape() {
    AndroidComposeTutorialTheme {
        Row {
            NavigationRailScreen()
            MainScreen()
        }
    }
}

// Custom Composables for Animation
@Composable
fun Modifier.fade(enable: Boolean): Modifier {
    val alpha by animateFloatAsState(if (enable) 0.5f else 1.0f)
    return this then Modifier.graphicsLayer { this.alpha = alpha }
}

/*
@Preview(
    showBackground = true, heightDp = 300, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "PreviewDark"
)
@Preview(showBackground = true, name = "PreviewLight", heightDp = 300)

 */
@Composable
private fun NavigationRailScreen(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home, contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.label_home))
                },
                selected = true,
                onClick = {
                    // Handle click
                    println("Clicked")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Face, contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.label_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}














