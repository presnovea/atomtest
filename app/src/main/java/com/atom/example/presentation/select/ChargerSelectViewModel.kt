package com.atom.example.presentation.select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.example.domain.ChargerInteractor
import com.atom.example.model.presentation.Charger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Вью модель экрана отображения зарядок для выбранного города
 *
 * @param chargersInter интерактор для работы с данными о зарядках
 */
@HiltViewModel
class ChargerSelectViewModel @Inject internal constructor(
    private val chargersInter: ChargerInteractor
) : ViewModel() {
    private val _chargerList: MutableStateFlow<List<Charger>> = MutableStateFlow(listOf())
    private val _events = Channel<ChargerSelectEvents>()

    /** Флоу состояний экрана (в данном случае только лист с заправками)*/
    val chargerList: StateFlow<List<Charger>> = _chargerList.asStateFlow()

    /** Флоу возможных событий*/
    val events: Flow<ChargerSelectEvents> = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            val chargers = chargersInter.getChargers()
            _chargerList.value = chargers
        }
    }

    /**
     * функция обработки действий пользователя
     */
    fun onAction(action: ChargerSelectActions) {
        when (action) {
            is ChargerSelectActions.BackButtonPressed -> { emitEvent(ChargerSelectEvents.GoBack) }
            is ChargerSelectActions.ItemPressed -> {
                _chargerList.value.find { it.id == action.id }?.let { charger ->
                    emitEvent(ChargerSelectEvents.ShowToast(charger.title))
                }
            }
        }
    }

    private fun emitEvent(event: ChargerSelectEvents) {
        viewModelScope.launch {
            _events.send(event)
        }
    }
}