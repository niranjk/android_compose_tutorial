package com.niranjan.khatri.androidcomposetutorial.ds.chips

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography

@Composable
fun NAssistChips(
    modifier: Modifier = Modifier,
    onAssistChipClick: () -> Unit,
    leadingIconDrawableRes: Int? = null,
    trailingIconDrawableRes: Int? = null,
    enabled: Boolean = true,
    chipLabel: String,
) {
    AssistChip(
        onClick = onAssistChipClick,
        leadingIcon = {
            if (leadingIconDrawableRes != null) {
                Icon(
                    modifier =
                        Modifier.size(
                            width = LocalShapes.current.space.spaceLarge,
                            height = LocalShapes.current.space.spaceLarge,
                        ),
                    painter = painterResource(id = leadingIconDrawableRes),
                    contentDescription = null,
                )
            }
        },
        trailingIcon = {
            if (trailingIconDrawableRes != null) {
                Icon(
                    modifier =
                        Modifier.size(
                            width = LocalShapes.current.space.spaceLarge,
                            height = LocalShapes.current.space.spaceLarge,
                        ),
                    painter = painterResource(id = trailingIconDrawableRes),
                    contentDescription = null,
                )
            }
        },
        label = {
            Text(text = chipLabel, maxLines = 1, style = LocalTypography.current.displayMedium)
        },
        colors =
            AssistChipDefaults.assistChipColors().copy(
                containerColor = LocalColorScheme.current.primary.primaryContainer,
                labelColor = LocalColorScheme.current.primary.primary,
            ),
        border =
            BorderStroke(
                width = 1.dp,
                color = LocalColorScheme.current.primary.onPrimaryContainer,
            ),
    )
}

@Preview
@Composable
fun NAssistChips_Preview() {
    var selected by remember {
        mutableStateOf(false)
    }
    NAssistChips(
        onAssistChipClick = {
            selected = !selected
        },
        leadingIconDrawableRes = R.drawable.ic_boy,
        chipLabel = "Assit Chips",
    )
}
