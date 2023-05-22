package com.capstone.techwasmark02.ui.theme.customShape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BottomNavShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            Path().apply {

                val widthInDp = with(density) { size.width.toDp() }
                val leftCircleStartPx = with(density) {
                    (widthInDp/2 - 50.dp).toPx()
                }
                val leftCircleEndPx = with(density) {
                    (widthInDp/2 - 40.dp).toPx()
                }
                val halfSmallRectHeight = with(density) {
                    10.dp.toPx()
                }
                val upperHalfBigRectHeight = with(density) {
                    30.dp.toPx()
                }
                val bottomHalfBigRectHeight = with(density) {
                    50.dp.toPx()
                }
                val rightCircleStartPx = with(density) {
                    (widthInDp/2 + 40.dp).toPx()
                }
                val rightCircleEndPx = with(density) {
                    (widthInDp/2 + 50.dp).toPx()
                }

                moveTo(x = 0f, y = 0f)
                lineTo(
                    x = leftCircleStartPx,
                    y = 0f
                )
                arcTo(
                    rect = Rect(
                        topLeft = Offset(
                            x = leftCircleStartPx,
                            y = 0f,
                        ),
                        bottomRight = Offset(
                            x = leftCircleEndPx,
                            y = halfSmallRectHeight,
                        )
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                arcTo(
                    rect = Rect(
                        topLeft = Offset(
                            x = leftCircleEndPx,
                            y = -upperHalfBigRectHeight,
                        ),
                        bottomRight = Offset(
                            x = rightCircleStartPx,
                            y = bottomHalfBigRectHeight,
                        )
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false
                )
                lineTo(
                    x = rightCircleStartPx,
                    y = halfSmallRectHeight
                )

                arcTo(
                    rect = Rect(
                        topLeft = Offset(
                            x = rightCircleStartPx,
                            y = 0f,
                        ),
                        bottomRight = Offset(
                            x = rightCircleEndPx,
                            y = halfSmallRectHeight
                        )
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
            }
        )
    }
}