package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.sheets

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NBottomsheet(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    imageUrl: String,
    onDismiss: () -> Unit,
    composableList: @Composable () -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .then(modifier),
        ) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 16.dp,
                    ),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Icon(
                    modifier =
                    Modifier
                        .wrapContentSize()
                        .clickable {
                            onDismiss()
                        },
                    painter = painterResource(id = R.drawable.ic_boy),
                    contentDescription = null,
                    tint = LocalColorScheme.current.primary.primary,
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                Modifier
                    .height(
                        256.dp,
                    )
                    .fillMaxWidth(),
            )
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = title,
                    style = LocalTypography.current.headlineLarge,
                    color = LocalColorScheme.current.primary.primary,
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = desc,
                    style = LocalTypography.current.bodySmall,
                    color = LocalColorScheme.current.primary.primary,
                )
                Spacer(modifier = Modifier.padding(16.dp))
                composableList()
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewMsaBottomSheet() {
    NBottomsheet(
        imageUrl = "https://picsum.photos/200/100",
        title = "Title",
        desc = "Lorem ipsum Lorem Ipsum Lorem Ipsum ......At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga. \n",
        onDismiss = {},
        composableList = {
            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) { Text(text = "Button 1") }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) { Text(text = "Button 2") }
        },
    )
}
