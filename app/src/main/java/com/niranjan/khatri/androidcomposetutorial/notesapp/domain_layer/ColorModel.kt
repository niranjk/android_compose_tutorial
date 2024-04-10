package com.niranjan.khatri.androidcomposetutorial.notesapp.domain_layer

import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.ColorDbModel

/**
 * @author NIRANJAN KHATRI
 * @since 26/03/24
 * @version 1
 */

/**
 * Model class for one Color
 */
data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {

    companion object {

        val DEFAULT = with(ColorDbModel.DEFAULT_COLOR) { ColorModel(id, name, hex) }
    }
}