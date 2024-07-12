package com.niranjan.khatri.androidcomposetutorial.ds.tag

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.niranjan.khatri.androidcomposetutorial.R

sealed class NTagStatus(
    val drawable: Int?,
) {
    class Default(
        defaultDrawable: Int = R.drawable.ic_car,
    ) : NTagStatus(drawable = defaultDrawable)

    class Boy :
        NTagStatus(
            drawable = R.drawable.ic_boy,
        )

    class Girl :
        NTagStatus(
            drawable = R.drawable.ic_girl,
        )
}

@Composable
fun NTagStatus.background() =
    when (this) {
        is NTagStatus.Default -> colorResource(id = R.color.colorAccent)
        is NTagStatus.Boy -> colorResource(id = R.color.colorPrimary)
        is NTagStatus.Girl -> colorResource(id = R.color.teal_200)
    }
