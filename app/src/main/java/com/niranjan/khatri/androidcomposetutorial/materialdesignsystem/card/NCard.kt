package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography

@Composable
fun ElevatedCardExample() {
    ElevatedCard(
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Cyan
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        CardContent()
    }
}

@Composable
fun ElevatedCardPreview() {
    InteractiveElevatedCard()
}

@Composable
fun InteractiveElevatedCard() {
    val interactionSource = remember { MutableInteractionSource() }
    ElevatedCard(
        onClick = { /* Action when card is clicked */ },
        interactionSource = interactionSource,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp,
            focusedElevation = 4.dp,
            hoveredElevation = 4.dp,
            draggedElevation = 4.dp,
            disabledElevation = 0.dp
        ),
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null, // You can customize the indication here
            onClick = { /* Action when card is clicked */ }
        )
    ) {
        CardContent()
    }
}

@Composable
private fun CardContent(){
    // Card content
    Column(Modifier.padding(8.dp)) {
        Icon(painter = painterResource(id = R.drawable.ic_boy),
            contentDescription = null)
        Text(text = "Header", style = LocalTypography.current.headlineMedium)
        Text(text = "Body - This is some body contents..", style = LocalTypography.current.bodySmall)
    }
}

@Composable
fun FilledCardExample() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan // Set your desired color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CardContent()
    }
}

@Composable
fun InteractiveFilledCard() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState() // Observe state here directly

    Card(
        onClick = { /* Action when card is clicked */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Customize indication if needed
                onClick = { /* Action when card is clicked */ }
            ),
        interactionSource = interactionSource,
        colors = CardDefaults.cardColors(
            containerColor = if (isPressed) Color.Cyan.copy(alpha = 0.8f) else Color.Cyan
        )
    ) {
        // Card content
        Text(
            text = "Interactive Filled Card",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun FilledCard_Preview(){
    InteractiveFilledCard()
}

@Composable
fun OutlinedCardExample() {
    OutlinedCard(border = BorderStroke(1.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(modifier = Modifier.fillMaxWidth(), painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Image")
            Text("Headline", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Sub Headline", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Supporting Text - This is some content inside the Outlined Card.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {  }) {
                Text(text = "Action Button")
            }
        }
    }
}

@Composable
fun CardPreview(){
    OutlinedCardExample()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    description: String,
    btnTxt: String,
    onClick: () -> Unit,
    selected : Boolean = false,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        onClick = onClick,modifier = modifier
            .semantics(mergeDescendants = true) {
                // Merge descendants for a single reading unit
                // Provide a description for screen readers
                contentDescription = "Card with title: $title, and description: $description"
                role = Role.Button
                stateDescription = if (selected) "Selected" else "Not Selected"
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "Expand", // Label for theaction
                        action = {
                            isExpanded = !isExpanded
                            true
                        } // Action to perform
                    )
                )
            }
            .padding(10.dp)
            .focusable() // Make the card focusable
            .onFocusChanged {
                // provide visual feedback when focus changed
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current // Use default visual indication
            ) { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            var isFocused by remember { mutableStateOf(false) }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable() // Make the Image focusable
                    .focusProperties {
                        canFocus = true
                    }.onFocusChanged {
                       isFocused = it.isFocused
                    }
                    .background(if (isFocused) Color.Yellow else Color.White), // Apply background color
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Image"
            )
            Text(title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(subtitle, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description)

            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {  }) {
                Text(text = btnTxt)
            }
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null, // Default description
                modifier = Modifier.clearAndSetSemantics {
                    contentDescription = "Add to favorites" // Override with custom description
                }
            )
        }
    }
}

@Preview
@Composable
fun MyCard_Preview(){
    MyCard(title = "Headline", subtitle = "Sub Headline", description = "Supporting Text - This is some content inside the Outlined Card.",
        btnTxt = "Action Button",
        onClick = {})
}