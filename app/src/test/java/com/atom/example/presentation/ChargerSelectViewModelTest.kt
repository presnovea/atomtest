package com.atom.example.presentation

import com.atom.example.data.ChargersRepository
import com.atom.example.domain.ChargerInteractor
import com.atom.example.model.City
import com.atom.example.model.presentation.Charger
import com.atom.example.presentation.select.ChargerSelectActions
import com.atom.example.presentation.select.ChargerSelectEvents
import com.atom.example.presentation.select.ChargerSelectViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class ChargerSelectViewModelTest {

    private val interactor: ChargerInteractor = mockk()
    private val repository: ChargersRepository = mockk()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val viewModel: ChargerSelectViewModel by lazy { ChargerSelectViewModel(interactor) }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `item pressed icon`() = runTest {
        coEvery { interactor.getChargers() } returns DEFAULT_VALUE
        val eventList = mutableListOf<ChargerSelectEvents>()
        val resList = mutableListOf<Charger>()
        val job = launch(UnconfinedTestDispatcher()) {
            viewModel.events.toList(eventList)
        }
        testScheduler.advanceTimeBy(6.seconds)

        viewModel.onAction(ChargerSelectActions.ItemPressed(DEFAULT_VALUE[0].id))

        assertThat(eventList).isEqualTo(ChargerSelectEvents.ShowToast(DEFAULT_VALUE[0].title))

        job.cancel()
    }

    @Test
    fun `back pressed`() = runTest {
        coEvery { interactor.getChargers() } returns DEFAULT_VALUE
        val eventList = mutableListOf<ChargerSelectEvents>()
        val job = launch(UnconfinedTestDispatcher()) {
            viewModel.events.toList(eventList)
        }
        testScheduler.advanceTimeBy(6.seconds)

        viewModel.onAction(ChargerSelectActions.BackButtonPressed)

        assertThat(eventList).isEqualTo(listOf(ChargerSelectEvents.GoBack))
        job.cancel()
    }

    companion object {
        private val DEFAULT_VALUE: List<Charger> =
            listOf(Charger(UUID.randomUUID(), false, "Test title", " Test address"))
    }
}