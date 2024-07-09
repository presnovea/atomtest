package com.atom.example.presentation.select

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.atom.example.R
import com.atom.example.model.presentation.Charger
import com.atom.example.presentation.NavRoute
import com.atom.example.presentation.item.ChargerListItem
import com.atom.example.presentation.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * Экран с информацией о зарядках
 *
 * @param navController
 * @param viewModel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChargerSelectScreen(
    navController: NavHostController,
    viewModel: ChargerSelectViewModel = hiltViewModel<ChargerSelectViewModel>()
) {
    val onAction: (ChargerSelectActions) -> Unit = remember { { viewModel.onAction(it) } }
    val snackBarHostState = remember { SnackbarHostState() }
    val chargerList = viewModel.chargerList.collectAsState()
    val scope = rememberCoroutineScope()

/** Обработка событий, которые могут происходить на экране */
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is ChargerSelectEvents.GoBack -> { navController.navigate(NavRoute.CitySelectScreen.path) }
                is ChargerSelectEvents.ShowToast -> {
                    scope.launch {
                        snackBarHostState.showSnackbar(event.text)
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleText() },
                navigationIcon = { NavigationButton(onAction) }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        ChargerList(
            modifier = Modifier.padding(innerPadding),
            onClick = onAction,
            chargerList.value
        )
    }
}

/** Название заправки */
@Composable
private fun TitleText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        style = AppTheme.typography.header,
        maxLines = 1,
        text = stringResource(id = R.string.charger_select_screen_title)
    )
}

/** Кнопка навигации */
@Composable
private fun NavigationButton(onClick: (ChargerSelectActions) -> Unit) {
    IconButton(onClick = { onClick(ChargerSelectActions.BackButtonPressed) }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
            contentDescription = null
        )
    }
}

/** Список заправок (для слажных больших списков нужно добавить оптимизацию для уменьшения рекомпозаций)  */
@Composable
private fun ChargerList(
    modifier: Modifier = Modifier,
    onClick: (ChargerSelectActions) -> Unit,
    chargers: List<Charger>
) {
    LazyColumn(
        state = rememberLazyListState(),
        modifier = modifier
    ) {
        items(chargers) { charger ->
            ChargerListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                onClick = { onClick(ChargerSelectActions.ItemPressed(charger.id)) },
                state = charger
            )
        }
    }
}
