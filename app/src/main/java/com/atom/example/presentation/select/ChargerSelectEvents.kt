package com.atom.example.presentation.select

/**
 * Возможные события экрана
 */
sealed interface ChargerSelectEvents {

    object GoBack : ChargerSelectEvents

    data class ShowToast(val text: String): ChargerSelectEvents
}