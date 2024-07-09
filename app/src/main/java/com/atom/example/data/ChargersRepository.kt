package com.atom.example.data

import android.content.res.AssetManager
import com.atom.example.data.remote.AtomTestApi
import com.atom.example.model.data.ChargersInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import javax.inject.Singleton

/**
 * Репозиторий данных о зарядках
 *
 * @param api Класс апи для получения данных из сети
 * @param assetManager Менеджер для работы с ассетами
 */
@Singleton
class ChargersRepository(
    private val api: AtomTestApi,
    private val assetManager: AssetManager
) {
    private val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val chargersList: Deferred<List<ChargersInfo>> = ioScope.async { getChargersData() }

    /**
     * блок инициализации. В задании написано, что загрузка должна происходить асинхронно при старте приложения.
     * Для примера этого достаточно, но вообще надо делать стартап таску.
     */
    init {
        chargersList.start()
    }

    /** Пытаемся получить данные из сети. Не получается, по этому берем из файла */
    private suspend fun getChargersData(): List<ChargersInfo> {

        delay(5000)

        val resList = mutableListOf<ChargersInfo>()
        try {
            val response = api.getChargers()
            resList.addAll(response.body() ?: listOf())
        } catch (ex: Exception) {
            resList.addAll(getDefaultChargers())
        }

        return resList.toList()
    }

    /** Функция чтения и парсинга JSON */
    private fun getDefaultChargers(): List<ChargersInfo> {
        val listChargersType = object : TypeToken<List<ChargersInfo>>() {}.type
        val json = assetManager.open("default_data.json").bufferedReader()

        val res: List<ChargersInfo> = Gson().fromJson(json, listChargersType)
        return res
    }

    companion object {
        private const val TAG = "ChargersRepository"
    }
}

