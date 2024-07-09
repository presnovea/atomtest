package com.atom.example.model.presentation

import java.util.UUID

/**
 * Класс - объект зарядки
 *  @param id айди зарядки
 *  @param isChargerBusy занята ли зарядка
 *  @param title название
 *  @param address адрес зарядки
 */
data class Charger(
    val id: UUID,
    val isChargerBusy: Boolean,
    val title: String,
    val address: String
)
