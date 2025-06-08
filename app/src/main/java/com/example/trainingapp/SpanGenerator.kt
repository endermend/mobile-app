package com.example.trainingapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannedString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity


class SpanGenerator(val context: Context, val text: String) {
    fun generate(sps: SpannableString, word: String, url: String? = null) {
        val start: Int = text.indexOf(word)
        val end: Int = start + word.length
        sps.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(context, "just word", Toast.LENGTH_SHORT).show();
                if (url == null)
                    return
                val intnt = Intent(Intent.ACTION_VIEW)
                intnt.data = Uri.parse(url)
                startActivity(context, intnt, null)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = context.getColor(R.color.purple_200)
                ds.isUnderlineText = false
            }
        }, start, end, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}