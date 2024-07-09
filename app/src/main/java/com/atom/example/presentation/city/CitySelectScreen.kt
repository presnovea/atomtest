package com.atom.example.presentation.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.atom.example.R
import com.atom.example.model.City
import com.atom.example.presentation.NavRoute
import com.atom.example.presentation.theme.AppTheme

/**
 * Экран выбора города
 * В более сложных экранах фактический юай лучше вынести в отдельную функцию
 * @param navController контроллер навигации
 * @param viewModel Вью модель экрана. Провайдится через обертку фабрики hiltViewModel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CitySelectScreen(
    navController: NavHostController,
    viewModel: CitySelectionViewModel = hiltViewModel<CitySelectionViewModel>()
) {
    val state = viewModel.cityState.collectAsState()
    var expanded by remember { mutableStateOf(state.value.isDataAvail) }
    var selectedText by remember { mutableStateOf("") }

    val citySelected: (String) -> Unit =
        remember { { selectedCity -> viewModel.updateSelectedCity(City(selectedCity)) } }
    val buttonClick: () -> Unit =
        remember { { if (selectedText.isNotEmpty()) navController.navigate(NavRoute.ChargerSelectScreen.path) } }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(bottom = 8.dp),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                state.value.cities.forEach { item ->
                    MenuItem(city = item) {
                        selectedText = it
                        expanded = !expanded
                        citySelected(it)
                    }
                }
            }
        }

        Button(
            enabled = selectedText.isNotEmpty(),
            onClick = { buttonClick() }) {
            Text(
                style = AppTheme.typography.button,
                maxLines = 1,
                text = stringResource(id = R.string.select_city_button_text)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun MenuItem(city: City, onClick: (String) -> Unit) {
    DropdownMenuItem(
        text = { ItemLabel(city.city) },
        onClick = { onClick(city.city) }
    )
}

@Composable
private fun ItemLabel(city: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        style = AppTheme.typography.subtitle2,
        maxLines = 1,
        text = city
    )
}

