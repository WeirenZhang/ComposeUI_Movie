package com.weiren.zhang.movie_composeui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun <T> LazyListScope.gridItems(
    data: List<T>,
    key: ((index: Int) -> Any)? = null,
    columnCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = key) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(1F, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}

@Composable
fun VerticalGrid(columnCount: Int, items: () -> List<(@Composable () -> Unit)>) {
    Column {
        items.invoke().windowed(columnCount, columnCount, true).forEach {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                it.forEach {
                    Box(modifier = Modifier.weight(1f, fill = true)) {
                        it.invoke()
                    }
                }
                if (it.size < columnCount) {
                    for (columnIndex in 0 until (columnCount - it.size)) {
                        Spacer(Modifier.weight(1F, fill = true))
                    }
                }
            }
        }
    }
}

