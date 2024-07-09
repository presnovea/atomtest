package com.atom.example

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Application-класс. Необходим для хилта
 */
@HiltAndroidApp
class AtomTestApplication : Application() {

}