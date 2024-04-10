package com.niranjan.khatri.androidcomposetutorial

import android.app.Application
import com.niranjan.khatri.androidcomposetutorial.notesapp.di.DependencyInjector

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
/**
 * Application class responsible for initializing the dependency injector.
 */
class ComposeTutorialApp : Application() {

    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}