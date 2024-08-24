package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.sheets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    modifier: Modifier = Modifier,
    topHeader: String,
    onDismiss: () -> Unit = {},
    content: @Composable (dismiss: () -> Unit) -> Unit,
) {
    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true, confirmValueChange = { newState ->
            newState != SheetValue.Hidden // Avoid The BottomSheet to be hidden.
        })
    ModalBottomSheet(
        sheetState = bottomSheetState,
        modifier = modifier.wrapContentSize(),
        containerColor = Color.Yellow,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        dragHandle = {},
    ) {
        // Bottom Sheet Content
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(modifier = Modifier.weight(1f), text = topHeader, style = LocalTypography.current.bodyLarge, color = Color.Black)
            Icon(
                modifier =
                Modifier
                    .wrapContentSize()
                    .minimumInteractiveComponentSize()
                    .clickable {
                        onDismiss()
                    },
                painter =  painterResource(id = com.niranjan.khatri.androidcomposetutorial.R.drawable.ic_car),
                contentDescription ="Close",
                tint = Color.Gray,
            )
        }
        content(onDismiss)
    }
}

@Composable
fun MyBottomSheetContent(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    imageUrl: String = "",
    textBtnLabel: String = "",
    textBtnClick: () -> Unit = {},
    btnLabel: String = "",
    btnAction: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        item {
            if (imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.46f),
                )
            }
        }
        item {
            Text(
                modifier =
                Modifier.padding(
                    10.dp
                ),
                text = title,
                style = LocalTypography.current.titleSmall,
                color = Color.Black,
            )
            Text(
                modifier =
                Modifier.padding(
                    10.dp
                ),
                text = desc,
                style = LocalTypography.current.titleSmall,
                color = Color.Black,
            )
            if (textBtnLabel.isNotEmpty()) {
                TextButton(
                    onClick = {
                        textBtnClick()
                    }
                ){
                    Text(text = "Text Button")
                }
            }
            if (btnLabel.isNotEmpty()) {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    onClick = {
                        btnAction()
                    }
                ){
                    Text(text = btnLabel)
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyBottomSheet() {
    MyBottomSheet(
        topHeader = "My Bottom Sheet",
        onDismiss = { /* Handle dismiss action */ },
        content = { dismiss ->
            MyBottomSheetContent(
                title = "BottomSheet Title",
                desc = "This is a sample bottom sheet description.",
                imageUrl = "https://picsum.photos/200/300",
                btnLabel = "Action A"
            )
        }
    )
}