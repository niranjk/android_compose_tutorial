package com.niranjan.khatri.androidcomposetutorial.mvi.intent

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */

sealed class FactIntent{
    object LoadFact : FactIntent()
}