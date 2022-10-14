package com.entropylabs.jiittimetable.helper

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.entropylabs.jiittimetable.R
import java.lang.Integer.max

fun flowLayoutMeasurePolicy() = MeasurePolicy{ measurables,constraints ->
    layout(constraints.maxWidth,constraints.maxHeight){
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        var yPos = 0
        var xPos = 0
        var maxY = 0
        placeables.forEach { placeable ->
            if (xPos + placeable.width >
                constraints.maxWidth
            ) {
                xPos = 0
                yPos += maxY
                maxY = 0
            }
            placeable.placeRelative(
                x = xPos,
                y = yPos
            )
            xPos += placeable.width
            if (maxY < placeable.height) {
                maxY = placeable.height
            }
        }
    }
}
@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    val measurePolicy = flowLayoutMeasurePolicy()
    Layout(measurePolicy = measurePolicy,
        content = content,
        modifier = modifier )
}
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: @Composable () -> Unit
) = Layout(content, modifier) { measurables, constraints ->
    val hGapPx = horizontalGap.roundToPx()
    val vGapPx = verticalGap.roundToPx()

    val rows = mutableListOf<MeasuredRow>()
    val itemConstraints = constraints.copy(minWidth = 0)

    for (measurable in measurables) {
        val lastRow = rows.lastOrNull()
        val placeable = measurable.measure(itemConstraints)

        if (lastRow != null && lastRow.width + hGapPx + placeable.width <= constraints.maxWidth) {
            lastRow.items.add(placeable)
            lastRow.width += hGapPx + placeable.width
            lastRow.height = max(lastRow.height, placeable.height)
        } else {
            val nextRow = MeasuredRow(
                items = mutableListOf(placeable),
                width = placeable.width,
                height = placeable.height
            )

            rows.add(nextRow)
        }
    }

    val width = rows.maxOfOrNull { row -> row.width } ?: 0
    val height = rows.sumBy { row -> row.height } + max(vGapPx.times(rows.size - 1), 0)

    val coercedWidth = width.coerceIn(constraints.minWidth, constraints.maxWidth)
    val coercedHeight = height.coerceIn(constraints.minHeight, constraints.maxHeight)

    layout(coercedWidth, coercedHeight) {
        var y = 0

        for (row in rows) {
            var x = when(alignment) {
                Alignment.Start -> 0
                Alignment.CenterHorizontally -> (coercedWidth - row.width) / 2
                Alignment.End -> coercedWidth - row.width

                else -> throw Exception("unsupported alignment")
            }

            for (item in row.items) {
                var localY = 0
                if (item.height < row.height) {
                    localY = when (verticalAlignment) {
                        Alignment.Start -> y
                        Alignment.CenterVertically -> y + ((row.height + vGapPx) / 2 - item.height / 2)
                        Alignment.End -> y + row.height
                        else -> throw Exception("unsupported alignment")
                    }
                } else localY = y
                item.place(x, localY)
                x += item.width + hGapPx
            }

            y += row.height + vGapPx
        }
    }
}

private data class MeasuredRow(
    val items: MutableList<Placeable>,
    var width: Int,
    var height: Int
)