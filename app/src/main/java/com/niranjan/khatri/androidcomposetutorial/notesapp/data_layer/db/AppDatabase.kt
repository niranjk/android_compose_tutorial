package com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.dao.ColorDao
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.dao.NoteDao
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.ColorDbModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.NoteDbModel

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
/**
 * App's database.
 *
 * It contains two tables: Note table and Color table.
 */
@Database(entities = [NoteDbModel::class, ColorDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "note-maker-database"
    }

    abstract fun noteDao(): NoteDao

    abstract fun colorDao(): ColorDao
}