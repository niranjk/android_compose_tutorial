package com.niranjan.khatri.androidcomposetutorial.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.R
import java.util.UUID
import kotlin.random.Random

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

@Composable
fun ListScreen(){
    MyList()
}

data class MyItem(
    val id: Int,
    val name : String,
    val timestamp : String
)
val itemList = listOf(
    MyItem(id = Random.nextInt(), "apple", "12:00" ),
    MyItem(id = Random.nextInt(), "ball", "13:00"),
    MyItem(id = Random.nextInt(), "cat", "14:00"),
)

@Preview
@Composable
fun ListItem(modifier: Modifier = Modifier, item: MyItem = itemList.first()){
    Column {
        Text(
            text = stringResource(id = R.string.compose),
            fontStyle = FontStyle.Normal,
            color = colorResource(id = R.color.purple_200),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.name,
            fontStyle = FontStyle.Normal,
            color = colorResource(id = R.color.purple_500),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.timestamp,
            fontStyle = FontStyle.Italic,
            color = colorResource(id = R.color.purple_700),
            fontSize = 20.sp,
            fontWeight = FontWeight.Thin
        )
        Button(onClick = { /*TODO*/ }) { Text(text = "MyButton") }
        Spacer(modifier = modifier.height(8.dp))
        Icon( Icons.Filled.Face, contentDescription = "Column Face")
    }
}
@Preview(showBackground = true)
@Composable
fun MyList(){
    LazyColumn{
        items(itemList){ item -> ListItem(item = item) }
    }
}