package com.atom.example.presentation.city

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.example.domain.ChargerInteractor
import com.atom.example.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Вью модель экрана выбора города
 * @param chargersInter  интерактор для работы с данными о зарядках
 */
@HiltViewModel
class CitySelectionViewModel @Inject internal constructor(
    private val chargersInter: ChargerInteractor
) : ViewModel() {

    private val _cityState = MutableStateFlow(CitySelectState())

    /** Флоу состояния экрана*/
    val cityState: StateFlow<CitySelectState> = _cityState.asStateFlow()

    /** Для примера достаточно просто вызова в конструкторе, а так, стоило бы получать значения через флоу*/
    init {
        viewModelScope.launch {
            val cities = chargersInter.getCities()

            _cityState.value = _cityState.value.copy(
                isDataAvail = cities.isNotEmpty(),
                cities = cities
            )
        }
    }

    /**
     * Передача выбранного города в интерактор
     */
    fun updateSelectedCity(city: City) {
        Log.w("AtomTag", "CitySelectionViewModel. updateSelectedCity")
        chargersInter.updateCurrentCity(city)
    }

}

