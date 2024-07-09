package com.atom.example.presentation.item

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.atom.example.model.presentation.Charger
import com.atom.example.presentation.theme.AppTheme
import com.atom.example.presentation.theme.AtomTestTheme
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Айтем листа зарядок
 */
@Composable
internal fun ChargerListItem(
    onClick: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    state: Charger
) {
    val cornerShape = RoundedCornerShape(16.dp)

    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = AppTheme.colors.outline,
                shape = cornerShape
            )
            .padding(all = 8.dp)
            .clickable(
                onClick = { onClick(state.id) },
                role = Role.Button
            )
            .clip(cornerShape),
        verticalAlignment = Alignment.CenterVertically
     ) {
        IsChargerBusy(sideSize = 24.dp, arcRadius = 8.dp, isBusy = state.isChargerBusy)
        ItemMainComponent(
            modifier = Modifier.padding(horizontal = 8.dp),
            title = state.title,
            address = state.address
        )
    }
}

/** Канвас, обоэначающий занятость зарядки */
@Composable
private fun IsChargerBusy(
    modifier: Modifier = Modifier,
    sideSize: Dp,
    arcRadius: Dp,
    isBusy: Boolean
) {
    Canvas(modifier = modifier.size(sideSize)) {
        val fillColor = if (isBusy) Color.Red else Color.Green
        val cornerRadius = CornerRadius(arcRadius.toPx(), arcRadius.toPx())
        Logger.getAnonymousLogger().log(Level.WARNING, "AtomTest. canvas width is: ${size.width}")

        drawRoundRect(
            fillColor,
            Offset(0f, 0f),
            Size(sideSize.toPx(), sideSize.toPx()),
            cornerRadius
        )
    }
}

/** Элемент с тестовками айтема */
@Composable
private fun ItemMainComponent(
    modifier: Modifier = Modifier,
    title: String,
    address: String
) {
    Column(modifier = modifier) {
        TitleText(title)
        AddressText(address)
    }
}

/** Заголовок */
@Composable
private fun TitleText(title: String) {
    Text(
        modifier = Modifier
            .padding(top = 8.dp),
        style = AppTheme.typography.body1,
        maxLines = 1,
        text = title
    )
}

/** Адрес */
@Composable
private fun AddressText(addr: String) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp),
        style = AppTheme.typography.subtitle2,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        text = addr
    )
}

@Preview
@Composable
private fun Preview() {
    AtomTestTheme {
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChargerListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    onClick = {UUID.randomUUID()},
                    state = Charger(
                        id = UUID.randomUUID(),
                        isChargerBusy = false,
                        title = "TitleText",
                        address = "Address"
                    )
                )
            }
        }
    }
}
