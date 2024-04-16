package com.niranjan.khatri.androidcomposetutorial.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.ui.theme.Shapes

/**
 * @author NIRANJAN KHATRI
 * @since 10/04/24
 * @version 1
 */

@Preview("Theme Light", showBackground = true)
@Preview("Theme Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MyIcon(
    @DrawableRes myIcon: Int = R.drawable.ic_launcher_foreground,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(Shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(myIcon),
        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.
        contentDescription = null
    )
}