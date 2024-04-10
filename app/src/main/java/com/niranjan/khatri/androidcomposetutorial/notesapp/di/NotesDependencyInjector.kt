package com.niranjan.khatri.androidcomposetutorial.notesapp.di

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
import android.content.Context
import androidx.room.Room
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.AppDatabase
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.mapper.DbMapper
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.db.mapper.DbMapperImpl
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.repository.Repository
import com.niranjan.khatri.androidcomposetutorial.notesapp.data_layer.repository.RepositoryImpl

/**
 * Provides dependencies across the app.
 */
class DependencyInjector(applicationContext: Context) {

    val repository: Repository by lazy { provideRepository(database) }

    private val database: AppDatabase by lazy { provideDatabase(applicationContext) }

    private val dbMapper: DbMapper = DbMapperImpl()

    private fun provideDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    private fun provideRepository(database: AppDatabase): Repository {
        val noteDao = database.noteDao()
        val colorDao = database.colorDao()

        return RepositoryImpl(noteDao, colorDao, dbMapper)
    }
}