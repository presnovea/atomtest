package com.atom.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.atom.example.model.presentation.Charger
import com.atom.example.presentation.item.ChargerListItem
import com.atom.example.presentation.theme.AtomTestTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

/**
 * Основная активити приложения
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AtomTestTheme {
                NavGraph(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AtomTestTheme {
        ChargerListItem(
            modifier = Modifier,
            onClick = { UUID.randomUUID() },
            state = Charger(
                id = UUID.randomUUID(),
                isChargerBusy = false,
                title = "TitleText",
                address = "Address"
            )
        )
    }
}