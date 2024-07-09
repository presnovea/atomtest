package com.atom.example.model.data

import com.google.gson.annotations.SerializedName

/**
 * Класс для получения данных запроса о зарядках
 */
data class ChargersInfo(
    @field:SerializedName("city") val city: String,
    @field:SerializedName("charger") val charger: ChargerData
)

/**
 * Класс отражающи сущность зарядки
 */
data class ChargerData(
    @field:SerializedName("busy") val busy: Boolean,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("address") val address: String
)