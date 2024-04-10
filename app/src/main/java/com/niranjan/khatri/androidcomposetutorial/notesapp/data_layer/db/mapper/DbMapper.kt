package com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.mapper

import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.ColorDbModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.NoteDbModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.domain_layer.ColorModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.domain_layer.NoteModel

/**
 * @author NIRANJAN KHATRI
 * @since 26/03/24
 * @version 1
 */

interface DbMapper {

    // NoteDbModel -> NoteModel

    fun mapNotes(
        noteDbModels: List<NoteDbModel>,
        colorDbModels: Map<Long, ColorDbModel>
    ): List<NoteModel>

    fun mapNote(noteDbModel: NoteDbModel, colorDbModel: ColorDbModel): NoteModel

    // ColorDbModel -> ColorModel

    fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel>

    fun mapColor(colorDbModel: ColorDbModel): ColorModel

    // NoteModel -> NoteDbModel

    fun mapDbNote(note: NoteModel): NoteDbModel

    // ColorModel -> ColorDbModel

    fun mapDbColors(colors: List<ColorModel>): List<ColorDbModel>

    fun mapDbColor(color: ColorModel): ColorDbModel
}