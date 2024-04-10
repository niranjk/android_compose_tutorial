package com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model

/**
 * @author NIRANJAN KHATRI
 * @since 26/03/24
 * @version 1
 */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "can_be_checked_off") val canBeCheckedOff: Boolean,
    @ColumnInfo(name = "is_checked_off") val isCheckedOff: Boolean,
    @ColumnInfo(name = "color_id") val colorId: Long,
    @ColumnInfo(name = "in_trash") val isInTrash: Boolean
) {

    companion object {

        val DEFAULT_NOTES = listOf(
            NoteDbModel(1, "ABC", "Test Sample", false, false, 1, false),
            NoteDbModel(2, "Story", "Story of my life", false, false, 2, false),
            NoteDbModel(3, "Eating", "Suger", false, false, 3, false),
            NoteDbModel(4, "Drinking", "Water,...", false, false, 4, false),
            NoteDbModel(5, "..", "Content 5", false, false, 5, false),)
    }
}