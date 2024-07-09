package com.atom.example.domain

import android.util.Log
import com.atom.example.data.ChargersRepository
import com.atom.example.model.City
import com.atom.example.model.presentation.Charger
import java.util.UUID
import javax.inject.Singleton

/**
 * Интерактор для работы с данными о зарядках
 *
 * @param chargerRepo Репозиторий данных о зарядках
 */
@Singleton
class ChargerInteractor(
    private val chargerRepo: ChargersRepository
) {
    private var currentCity: City = City()

    /**
     * Получить города
     */
    suspend fun getCities(): List<City> {
        val list = chargerRepo.chargersList.await()
        val resList = list.map { City(it.city) }.sortedBy { it.city }.distinct()

        return resList
    }

    /**
     * Записать выбранный пользователем город
     */
    fun updateCurrentCity(city: City) =
        city.let { if (it.city.isNotEmpty()) currentCity = it }


    /**
     * Получить зарядки на основании выбранного города
     */
    suspend fun getChargers(): List<Charger> {
        val list = chargerRepo.chargersList.await()

        val resList = list.filter { it.city == currentCity.city }
            .map { it.charger }
            .map {
                Charger(
                    id = UUID.randomUUID(),
                    isChargerBusy = it.busy,
                    title = it.name,
                    address = it.address
                )
            }

        return resList
    }

    companion object {
        private const val TAG = "ChargerInteractor"
    }
}