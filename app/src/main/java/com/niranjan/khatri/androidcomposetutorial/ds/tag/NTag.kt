package com.niranjan.khatri.androidcomposetutorial.ds.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.niranjan.khatri.androidcomposetutorial.ds.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography

@Composable
fun NTag(
    modifier: Modifier = Modifier,
    text: String,
    contentDesc: String? = null,
    tagModel: NTagStatus,
) {
    Row(
        modifier =
            modifier
                .wrapContentWidth()
                .background(color = Color.Transparent, shape = RoundedCornerShape(LocalShapes.current.radius.radiusSmall))
                .padding(LocalShapes.current.space.spaceSmall)
                .semantics(mergeDescendants = true) { },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tagModel.drawable?.let {
            Icon(
                painterResource(id = it),
                modifier =
                    modifier.padding(end = LocalShapes.current.space.spaceSmall).size(
                        LocalShapes.current.space.spaceXLarge,
                    ),
                contentDescription = contentDesc,
            )
        }
        Text(
            color = Color.Blue,
            text = text,
            maxLines = 1,
            textAlign = TextAlign.Center,
            style = LocalTypography.current.labelSmall,
        )
    }
}

@DevicePreview
@Composable
fun Tag_P() {
    Column {
        NTag(text = "Girl", tagModel = NTagStatus.Girl())
    }
}
