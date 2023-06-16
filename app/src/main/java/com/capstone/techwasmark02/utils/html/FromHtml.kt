package com.capstone.techwasmark02.utils.html

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import com.capstone.techwasmark02.R

fun fromHtml(context: Context, html: String): Spannable = parse(html).apply {
    removeLinksUnderline()
    styleBold(context)
}

private fun parse(html: String): Spannable =
    (HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) as Spannable)

private fun Spannable.removeLinksUnderline() {
    for (s in getSpans(0, length, URLSpan::class.java)) {
        setSpan(object : UnderlineSpan() {
            override fun updateDrawState(tp: TextPaint) {
                tp.isUnderlineText = false
            }
        }, getSpanStart(s), getSpanEnd(s), 0)
    }
}

private fun Spannable.styleBold(context: Context) {
    val bold = ResourcesCompat.getFont(context, R.font.poppins_semi_bold)!!
    for (s in getSpans(0, length, StyleSpan::class.java)) {
        if (s.style == Typeface.BOLD) {
            setSpan(ForegroundColorSpan(Color.BLACK), getSpanStart(s), getSpanEnd(s), 0)
            setSpan(bold.getTypefaceSpan(), getSpanStart(s), getSpanEnd(s), 0)
        }
    }
}

