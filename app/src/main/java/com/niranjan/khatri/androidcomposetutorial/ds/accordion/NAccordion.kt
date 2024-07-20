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
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ds.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.ds.theme.NAppTheme

@Composable
fun NAccordion(
    title: String,
    content: String,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column {
        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                    }.padding(LocalShapes.current.space.spaceLarge),
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Icon(imageVector = if (expanded == null) Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
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
fun AccordionN() {
    NAppTheme {
        NAccordion(title = "Accordion ", content = "This is my content!")
    }
}
