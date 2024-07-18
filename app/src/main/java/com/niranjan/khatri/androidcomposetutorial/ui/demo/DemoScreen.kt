package com.niranjan.khatri.androidcomposetutorial.ui.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography

@Composable
fun DemoScreen(
    modifier: Modifier = Modifier,
    navigateToItemScreen: (DemoItemType) -> Unit,
) {
    val demoItemsList =
        listOf<DemoItemType>(
            DemoItemType.Tag,
            DemoItemType.Tab,
        )
    LazyColumn {
        items(demoItemsList) { item ->
            Column {
                Row(
                    modifier =
                        modifier
                            .fillMaxSize()
                            .background(LocalColorScheme.current.neutral.background)
                            .padding(
                                LocalShapes.current.space.spaceMedium,
                            ).clickable {
                                navigateToItemScreen(item)
                            },
                ) {
                    Text(
                        modifier = modifier,
                        text = item.name,
                        color = LocalColorScheme.current.primary.onPrimary,
                        style = LocalTypography.current.bodyLarge,
                    )
                }
            }
        }
    }
}

enum class DemoItemType {
    Tag,
    Tab,
}
