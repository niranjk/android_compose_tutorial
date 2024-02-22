package com.niranjan.khatri.androidcomposetutorial.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niranjan.androidtutorials.mvc.controller.TodoMVCActivity
import com.niranjan.khatri.androidcomposetutorial.clean_architecture.CleanArchitectureActivity
import com.niranjan.khatri.androidcomposetutorial.mvi.FactMVIActivity
import com.niranjan.khatri.androidcomposetutorial.mvp.ShoppingMVPActivity
import com.niranjan.khatri.androidcomposetutorial.mvvm.DogMVVMActivity

/**
 * @author NIRANJAN KHATRI
 * @since 16/02/24
 * @version 1
 */
@Composable
fun ClickableColumn(context: Context) {
    val featureList by remember { mutableStateOf(featureList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        featureList.forEach { todo ->
            FeatureItemView(context, featureItem = todo)
        }
    }
}

@Composable
fun FeatureItemView(context: Context, featureItem: FeatureItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                handleClick(context, featureItem.title)
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = featureItem.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = featureItem.description, style = MaterialTheme.typography.headlineSmall)
        }
    }
}
private fun featureList(): List<FeatureItem> {
    return listOf(
        FeatureItem("MVC", "Model View Controller Demo"),
        FeatureItem("MVP", "Model View Presenter Demo"),
        FeatureItem("MVVM", "Model View ViewModel Demo"),
        FeatureItem("MVI", "Model View Intent Demo"),
        FeatureItem("Clean Architecture", "Clean Architecture Demo"),
    )
}

private fun handleClick(context: Context, value: String){
    when(value){
        "MVC" -> {
            context.startActivity(Intent(context, TodoMVCActivity::class.java))
        }
        "MVP" -> {
            context.startActivity(Intent(context, ShoppingMVPActivity::class.java))
        }
        "MVVM" -> {
            context.startActivity(Intent(context, DogMVVMActivity::class.java))
        }
        "MVI" -> {
            context.startActivity(Intent(context, FactMVIActivity::class.java))
        }
        "Clean Architecture" -> {
            context.startActivity(Intent(context, CleanArchitectureActivity::class.java))
        }
    }
}
data class FeatureItem(
    val title: String,
    val description: String
)
