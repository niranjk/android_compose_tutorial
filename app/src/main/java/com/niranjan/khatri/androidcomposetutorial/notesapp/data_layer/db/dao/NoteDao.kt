package com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.NoteDbModel
import kotlinx.coroutines.flow.Flow

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
/**
 * Dao for managing Note table in the database.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteDbModel")
    suspend fun getAllSync(): List<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id IN (:noteIds)")
    suspend fun getNotesByIdsSync(noteIds: List<Long>): List<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id LIKE :id")
    fun findById(id: Long): Flow<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id LIKE :id")
    suspend fun findByIdSync(id: Long): NoteDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDbModel: NoteDbModel)

    @Insert
    suspend fun insertAll(vararg noteDbModel: NoteDbModel)

    @Query("DELETE FROM NoteDbModel WHERE id LIKE :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM NoteDbModel WHERE id IN (:noteIds)")
    suspend fun delete(noteIds: List<Long>)
}