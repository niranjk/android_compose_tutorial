package com.niranjan.khatri.androidcomposetutorial.ds.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class TextModel {
    class EmptyStringModel : TextModel()

    data class ResStringModel(
        @StringRes val resId: Int,
    ) : TextModel()

    data class StringModel(
        val text: String,
    ) : TextModel()

    @Composable
    fun text() =
        when (this) {
            is EmptyStringModel -> ""
            is ResStringModel -> stringResource(id = resId)
            is StringModel -> text
        }
}
