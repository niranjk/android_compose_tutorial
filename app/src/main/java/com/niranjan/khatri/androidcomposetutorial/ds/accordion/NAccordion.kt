package com.niranjan.khatri.androidcomposetutorial.ds.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.ds.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography
import com.niranjan.khatri.androidcomposetutorial.ds.theme.NAppTheme

typealias OnExpanded = ((Boolean) -> Unit)

@Composable
fun NAccordion(
    title: String,
    content: String,
    expanded: Boolean,
    onRowExpanded: OnExpanded,
) {
    val accessibilityLabel =
        if (expanded) stringResource(id = R.string.label_expanded) else stringResource(R.string.label_collapsed)
    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .semantics (mergeDescendants = true){
                        stateDescription = accessibilityLabel
                    }.clickable {
                        onRowExpanded(expanded)
                    }.padding(LocalShapes.current.space.spaceLarge),
        ) {
            Text(text = title, modifier = Modifier.weight(1f), style = LocalTypography.current.headlineMedium)
            Icon(
                imageVector =
                    if (expanded) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                contentDescription = null,
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = content)
            }
        }
    }
}

@DevicePreview
@Composable
fun accordionN() {
    var expanded by remember {
        mutableStateOf(false)
    }
    NAppTheme {
        NAccordion(title = "Accordion Header", content = "This is my content!", expanded) {
            expanded = !expanded
        }
    }
}
