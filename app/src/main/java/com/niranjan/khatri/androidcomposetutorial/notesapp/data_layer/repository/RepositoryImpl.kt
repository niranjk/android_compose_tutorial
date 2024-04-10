package com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.repository

import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.dao.ColorDao
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.dao.NoteDao
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.mapper.DbMapper
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.ColorDbModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.model.NoteDbModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.domain_layer.ColorModel
import com.niranjan.khatri.androidcomposetutorial.notesapp.domain_layer.NoteModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
class RepositoryImpl(
    private val noteDao: NoteDao,
    private val colorDao: ColorDao,
    private val dbMapper: DbMapper
) : Repository {

    private val notesNotInTrashLiveData: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    private val notesInTrashLiveData: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    init {
        initDatabase(this::updateNotesLiveData)
    }

    /**
     * Populates database with colors if it is empty.
     */
    private fun initDatabase(postInitAction: suspend () -> Unit) {
        GlobalScope.launch {
            // Prepopulate colors
            val colors = ColorDbModel.DEFAULT_COLORS.toTypedArray()
            val dbColors = colorDao.getAllSync()
            if (dbColors.isEmpty()) {
                colorDao.insertAll(*colors)
            }

            // Prepopulate notes
            val notes = NoteDbModel.DEFAULT_NOTES.toTypedArray()
            val dbNotes = noteDao.getAllSync()
            if (dbNotes.isEmpty()) {
                noteDao.insertAll(*notes)
            }

            postInitAction.invoke()
        }
    }

    override fun getAllNotesNotInTrash(): Flow<List<NoteModel>> = notesNotInTrashLiveData

    override fun getAllNotesInTrash(): Flow<List<NoteModel>> = notesInTrashLiveData

    private suspend fun getAllNotesDependingOnTrashStateSync(inTrash: Boolean): List<NoteModel> {
        val colorDbModels: Map<Long, ColorDbModel> = colorDao.getAllSync().associateBy { it.id }
        val dbNotesNotInTrash: List<NoteDbModel> =
            noteDao.getAllSync().filter { it.isInTrash == inTrash }
        return dbMapper.mapNotes(dbNotesNotInTrash, colorDbModels)
    }

    override fun getNote(id: Long): Flow<NoteModel> = noteDao.findById(id)
        .map {
            val colorDbModel = colorDao.findByIdSync(it.colorId)
            dbMapper.mapNote(it, colorDbModel)
        }

    override suspend fun insertNote(note: NoteModel) {
        noteDao.insert(dbMapper.mapDbNote(note))
        updateNotesLiveData()
    }

    override suspend fun deleteNote(id: Long) {
        noteDao.delete(id)
        updateNotesLiveData()
    }

    override suspend fun deleteNotes(noteIds: List<Long>) {
        noteDao.delete(noteIds)
        updateNotesLiveData()
    }

    override suspend fun moveNoteToTrash(noteId: Long) {
        val dbNote = noteDao.findByIdSync(noteId)
        val newDbNote = dbNote.copy(isInTrash = true)
        noteDao.insert(newDbNote)
        updateNotesLiveData()
    }

    override suspend fun restoreNotesFromTrash(noteIds: List<Long>) {
        val dbNotesInTrash = noteDao.getNotesByIdsSync(noteIds)
        dbNotesInTrash.forEach {
            val newDbNote = it.copy(isInTrash = false)
            noteDao.insert(newDbNote)
        }
        updateNotesLiveData()
    }

    override fun getAllColors(): Flow<List<ColorModel>> = colorDao.getAll()
        .map { dbMapper.mapColors(it) }

    override suspend fun getAllColorsSync(): List<ColorModel> =
        dbMapper.mapColors(colorDao.getAllSync())

    override fun getColor(id: Long): Flow<ColorModel> = colorDao.findById(id)
        .map { dbMapper.mapColor(it) }

    override suspend fun getColorSync(id: Long): ColorModel =
        dbMapper.mapColor(colorDao.findByIdSync(id))

    private suspend fun updateNotesLiveData() {
        notesNotInTrashLiveData.update { getAllNotesDependingOnTrashStateSync(inTrash = false) }
        notesInTrashLiveData.update { getAllNotesDependingOnTrashStateSync(inTrash = true) }
    }
}