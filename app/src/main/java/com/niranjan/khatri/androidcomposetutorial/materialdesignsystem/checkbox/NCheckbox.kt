package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography

@Composable
fun GamePreferences() {
    var football by remember {  mutableStateOf(ToggleableState.Off) }
    var basketball by remember {  mutableStateOf(ToggleableState.Off) }
    var cricket by remember {  mutableStateOf(ToggleableState.Off) } // Cricket is initially selected
    var tennis by remember { mutableStateOf(ToggleableState.Off) }
    var hockey by remember {  mutableStateOf(ToggleableState.Off) }

    Column {
        Text(text ="Select your favorite games:", style = LocalTypography.current.headlineLarge)
        NCheckbox(state = football, onClick = {
            football =
                when (football) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
        }, label = "Football")
        NCheckbox(state = basketball, onClick = {
            basketball =
                when (basketball) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
        }, label = "Basketball")
        NCheckbox(state = cricket, onClick = {
            cricket =
                when (cricket) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
        }, label = "Cricket")
        NCheckbox(state = tennis, onClick = {
            tennis =
                when (tennis) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
        }, label = "Tennis")
        NCheckbox(state = hockey, onClick = {
            hockey =
                when (hockey) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
        }, label = "Hockey")
    }
}

typealias OnCheckboxClick = () -> Unit

@Composable
private fun NCheckbox(
    modifier: Modifier = Modifier,
    state: ToggleableState,
    enabled: Boolean = true,
    label: String = "",
    isOutlined: Boolean = false,
    onClick: OnCheckboxClick,
) {
    Row(
        modifier
            .triStateToggleable(
                state = state,
                enabled = enabled,
                role = Role.Checkbox,
                onClick = onClick,
            )
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        TriStateCheckbox(
            state = state,
            onClick = null,
            colors =
            CheckboxColors(
                checkedCheckmarkColor =
                when {
                    isOutlined && enabled -> LocalColorScheme.current.primary.primary
                    isOutlined && !enabled -> LocalColorScheme.current.primary.onPrimary
                    else -> LocalColorScheme.current.primary.inversePrimary
                },
                disabledCheckedBoxColor = if (isOutlined) Color.Transparent else LocalColorScheme.current.primary.inversePrimary,
                checkedBorderColor = LocalColorScheme.current.primary.inversePrimary,
                uncheckedBorderColor = LocalColorScheme.current.primary.inversePrimary,
                disabledBorderColor = LocalColorScheme.current.primary.inversePrimary,
                disabledUncheckedBorderColor = LocalColorScheme.current.primary.inversePrimary,
                disabledIndeterminateBorderColor = LocalColorScheme.current.primary.inversePrimary,
                uncheckedCheckmarkColor = LocalColorScheme.current.primary.primary,
                checkedBoxColor = LocalColorScheme.current.primary.primary,
                uncheckedBoxColor = Color.Transparent,
                disabledUncheckedBoxColor = Color.Transparent,
                disabledIndeterminateBoxColor = if (isOutlined) Color.Transparent else LocalColorScheme.current.primary.inversePrimary,),
            enabled = enabled,
        )
        Text(
            label,
            modifier =
            modifier
                .align(Alignment.CenterVertically)
                .padding(start = 12.dp),
            color = LocalColorScheme.current.primary.inversePrimary,
            style = LocalTypography.current.headlineMedium,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
fun  MyCheckbox_Preview(){
    GamePreferences()
}