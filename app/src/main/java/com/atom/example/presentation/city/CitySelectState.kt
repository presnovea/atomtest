package com.atom.example.presentation.city

import com.atom.example.model.City
import java.util.UUID

/**
 * Стейт экрпна выбора города
 */
data class CitySelectState(
    val isDataAvail: Boolean  = false, //TODO(ДОделать работу со свойством)
    val cities: List<City> = listOf(City("Moscow"), City("Paris"), City("Springfield"))
)
