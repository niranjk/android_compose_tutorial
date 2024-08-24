package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.selectors

import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.DevicePreview
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.NAppTheme

typealias OnSwitched = ((Boolean) -> Unit)

@Composable
fun NSwitchLabel(
    modifier: Modifier = Modifier,
    state: Boolean = false,
    enabled: Boolean = true,
    label: String = "",
    onSwitched: OnSwitched,
) {
    val context = LocalContext.current
    val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    val isTalkBackEnabled by remember {
        mutableStateOf(accessibilityManager.isEnabled && accessibilityManager.isTouchExplorationEnabled)
    }

    Row(
        modifier
            .then(
                if (isTalkBackEnabled) { // If TalkBack enabled focus the entire Row Item Toggleable
                    Modifier.toggleable(
                        value = state,
                        enabled = enabled,
                        role = Role.Switch,
                        onValueChange = onSwitched,
                    )
                } else {
                    Modifier
                },
            ).padding(LocalShapes.current.space.spaceMedium)
            .fillMaxWidth(),
    ) {
        Switch(
            checked = state,
            onCheckedChange = if (!isTalkBackEnabled) onSwitched else null, // Only Switch Item Toggleable When TalkBack Disabled
            colors =
                SwitchDefaults.colors(
                    checkedTrackColor = LocalColorScheme.current.neutral.onBackground,
                    uncheckedBorderColor = LocalColorScheme.current.neutral.background,
                    uncheckedThumbColor = LocalColorScheme.current.other.surfaceVariant,
                    checkedThumbColor = LocalColorScheme.current.other.surfaceVariant,
                    uncheckedTrackColor = LocalColorScheme.current.neutral.onBackground,
                    disabledCheckedTrackColor = LocalColorScheme.current.neutral.onBackground,
                    disabledUncheckedBorderColor = LocalColorScheme.current.primary.inversePrimary,
                    disabledUncheckedTrackColor = LocalColorScheme.current.neutral.onBackground,
                    disabledUncheckedThumbColor = LocalColorScheme.current.other.surfaceContainer,
                    disabledCheckedThumbColor = LocalColorScheme.current.other.surfaceVariant,
                ),
            enabled = enabled,
        )
        if (label.isNotEmpty()) {
            Text(
                text = label,
                modifier = modifier.align(Alignment.CenterVertically).padding(LocalShapes.current.space.spaceMedium),
                color = LocalColorScheme.current.neutral.onBackground,
                style = LocalTypography.current.bodyMedium,
                maxLines = 1,
            )
        }
    }
}

@DevicePreview
@Composable
fun demoSwitch() {
    NAppTheme {
        Column {
            NSwitchLabel(state = true, label = "MY Switch", onSwitched = {})
            NSwitchLabel(onSwitched = {})
            NSwitchLabel(state = true, enabled = false, label = "My Disabled Switch", onSwitched = {})
            NSwitchLabel(state = false, enabled = false, onSwitched = {})
        }
    }
}
