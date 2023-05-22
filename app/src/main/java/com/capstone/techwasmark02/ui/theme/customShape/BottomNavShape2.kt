package com.capstone.techwasmark02.ui.theme.customShape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class BottomNavShape2: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            Path().apply {

                val center = size.width/2

                val smallAngle = 60
                val smallAngleCalcRad = ((90 - smallAngle) * PI / 180).toFloat()
                val sinSmallAngleCalc = sin(smallAngleCalcRad)
                val cosSmallAngleCalc = cos(smallAngleCalcRad)

                val bigAngle = 120
                val bigAngleCalcRad = ((bigAngle/2) * PI / 180).toFloat()
                val sinBigAngleCalc = sin(bigAngleCalcRad)

                val initialGabWidth = with(density) {
                    82.dp.toPx()
                }
                val initialGabR = initialGabWidth/2

                val smallRectWidth = with(density) {
                    20.dp.toPx()
                }
                val smallRectR = smallRectWidth/2

                val gapR = (initialGabR + (1 - cosSmallAngleCalc) * smallRectR) / sinBigAngleCalc

                val bigCircleMiddlePoint =  - ((1 - sinSmallAngleCalc) * smallRectR)

                val bigCircleStartX = center - gapR
                val bigCircleStartY = bigCircleMiddlePoint + gapR

                val bigCircleEndX = center + gapR
                val bigCircleEndY = bigCircleMiddlePoint - gapR

                val leftSmallRectStartX = center - initialGabR - smallRectWidth + ((1 - cosSmallAngleCalc) * smallRectR)
                val leftSmallRectEndX = center - initialGabR + ((1 - cosSmallAngleCalc) * smallRectR)

                val rightSmallRectStartX = center + initialGabR + smallRectWidth - ((1 - cosSmallAngleCalc) * smallRectR)
                val rightSmallRectEndX = center + initialGabR - ((1 - cosSmallAngleCalc) * smallRectR)

                moveTo(x = 0f, y = 0f)
                lineTo(
                    x = leftSmallRectStartX,
                    y = 0f
                )
                arcTo(
                    rect = Rect(
                        topLeft = Offset(
                            x = leftSmallRectStartX,
                            y = 0f,
                        ),
                        bottomRight = Offset(
                            x = leftSmallRectEndX,
                            y = smallRectWidth,
                        )
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 60f,
                    forceMoveTo = false
                )

                lineTo(
                    x = rightSmallRectStartX,
                    y = bigCircleMiddlePoint
                )

//                arcTo(
//                    rect = Rect(
//                        topLeft = Offset(
//                            x = bigCircleStartX,
//                            y = bigCircleStartY,
//                        ),
//                        bottomRight = Offset(
//                            x = bigCircleEndX,
//                            y = bigCircleEndY,
//                        )
//                    ),
//                    startAngleDegrees = 150f,
//                    sweepAngleDegrees = -90f,
//                    forceMoveTo = false
//                )

                arcTo(
                    rect = Rect(
                        topLeft = Offset(
                            x = rightSmallRectStartX,
                            y = 0f,
                        ),
                        bottomRight = Offset(
                            x = rightSmallRectEndX,
                            y = smallRectWidth
                        )
                    ),
                    startAngleDegrees = 210f,
                    sweepAngleDegrees = 60f,
                    forceMoveTo = false
                )

                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
            }
        )
    }
}