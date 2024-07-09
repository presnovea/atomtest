package com.atom.example.data.remote

import com.atom.example.model.data.ChargersInfo
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Класс апи для получения данных из сети
 */
interface AtomTestApi {

    /**
     * Запрос получения данных о зарядках
     */
    @GET("/api/v1/getchargers")
    suspend fun getChargers(): Response<List<ChargersInfo>>

    companion object {

        private const val URL = "https://atomtest.ru/"

        /**
         * Метод получения апи с генерацией всех необходимых объектов
         */
        internal fun create(): AtomTestApi {
            val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val converter = GsonConverterFactory.create(GsonBuilder().create())
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(converter)
                .client(httpClient).build()
                .create(AtomTestApi::class.java)

            return retrofit
        }
    }
}