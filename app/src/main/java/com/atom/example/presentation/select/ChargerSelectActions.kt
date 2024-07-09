package com.atom.example.presentation.select

import java.util.UUID

/**
 * Возможные действия пользователя
 */
sealed interface ChargerSelectActions {
    object BackButtonPressed: ChargerSelectActions

    data class ItemPressed(val id: UUID): ChargerSelectActions
}