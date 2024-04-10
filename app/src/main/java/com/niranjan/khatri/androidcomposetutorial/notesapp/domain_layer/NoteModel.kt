package com.niranjan.khatri.androidcomposetutorial.notesapp.domain_layer

/**
 * @author NIRANJAN KHATRI
 * @since 26/03/24
 * @version 1
 */
const val NEW_NOTE_ID = -1L

/**
 * Model class that represents one Note
 */
data class NoteModel(
    val id: Long = NEW_NOTE_ID, // This value is used for new notes
    val title: String = "",
    val content: String = "",
    val isCheckedOff: Boolean? = null, // null represents that the note can't be checked off
    val color: ColorModel = ColorModel.DEFAULT
)