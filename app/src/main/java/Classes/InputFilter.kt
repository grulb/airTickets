package Classes

import android.text.InputFilter
import android.text.Spanned

class InputFilter: InputFilter {
    private val pattern = Regex("^[\\u0400-\\u04FF\\s-]+$")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int,
    ): CharSequence {
        if (source == null) return ""
        val builder = StringBuilder(source.subSequence(start, end))
        for (i in start until end) {
            val substring = source.subSequence(i, i + 1)
            if (!pattern.containsMatchIn(substring)) {
                builder.deleteCharAt(i - start)
            }
        }
        return builder.toString()
    }
}