package com.niranjan.khatri.androidcomposetutorial.ui.demo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
            DemoItemType.Accordion,
            DemoItemType.Divider,
            DemoItemType.Switch,
            DemoItemType.Tab,
            DemoItemType.Tag,
        )
    LazyColumn {
        items(demoItemsList) { item ->
            Card(
                modifier =
                    modifier
                        .fillMaxSize()
                        .padding(
                            LocalShapes.current.space.spaceMedium,
                        ).clickable {
                            navigateToItemScreen(item)
                        },
                border =
                    BorderStroke(
                        LocalShapes.current.space.spaceSmall,
                        color = LocalColorScheme.current.primary.primaryContainer,
                    ),
            ) {
                Text(
                    modifier = modifier.padding(LocalShapes.current.space.spaceMedium),
                    text = item.name,
                    color = LocalColorScheme.current.primary.onPrimary,
                    style = LocalTypography.current.bodyLarge,
                )
            }
        }
    }
}

enum class DemoItemType {
    Accordion,
    Divider,
    Switch,
    Tag,
    Tab,
}
