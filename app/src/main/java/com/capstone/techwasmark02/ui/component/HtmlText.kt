package com.capstone.techwasmark02.ui.component

import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.utils.html.fromHtml
import kotlin.math.max

private const val SPACING_FIX = 3f

@Composable
fun HtmlText(
    modifier: Modifier = Modifier,
    html: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLine: Int? = null
) {
    AndroidView(
        modifier = modifier,
        update = { it.text = fromHtml(it.context, html) },
        factory = { context ->
            val spacingReady =
                max(textStyle.lineHeight.value - textStyle.fontSize.value - SPACING_FIX, 0f)
            val extraSpacing = spToPx(spacingReady.toInt(), context)
            val gravity = when (textStyle.textAlign) {
                TextAlign.Center -> Gravity.CENTER
                TextAlign.End -> Gravity.END
                else -> Gravity.START
            }
            val fontResId = when (textStyle.fontWeight) {
                FontWeight.Medium -> R.font.poppins_medium
                else -> R.font.poppins_regular
            }
            val font = ResourcesCompat.getFont(context, fontResId)

            TextView(context).apply {
                // general style
                textSize = textStyle.fontSize.value
                setLineSpacing(extraSpacing, 1f)
                setTextColor(textStyle.color.toArgb())
                setGravity(gravity)
                typeface = font
                if (maxLine != null) {
                    maxLines = maxLine
                    ellipsize = TextUtils.TruncateAt.END
                }
            }
        }
    )
}

fun spToPx(sp: Int, context: Context): Float =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp.toFloat(),
        context.resources.displayMetrics
    )