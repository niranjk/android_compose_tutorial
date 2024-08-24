package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.selectors

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun BasicSwitch() {
    var isChecked by remember { mutableStateOf(false) }

    Switch(
        // checked parameter represents the current state of the switch true: "on" or false: "off"
        checked = isChecked,
        // this lambda function is called when the User interacts with the Switch. toggling it's state.
        // the new checked state is passed as a parameter to this lambda.
        onCheckedChange = { isChecked = it },
    )
}
