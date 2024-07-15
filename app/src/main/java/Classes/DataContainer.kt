package Classes

import android.content.Context
import android.content.SharedPreferences

object DataContainer {
    private const val PREF_NAME = "user_preferences"
    private const val FROM_TEXT_KEY = "fromText"
    private const val WHERE_TEXT_KEY = "whereText"
    private const val DATE_TEXT_KEY = "flightDate"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveData(context: Context, fromText: String, whereText: String) {
        val editor = getPreferences(context).edit()
        editor.putString(FROM_TEXT_KEY, fromText)
        editor.putString(WHERE_TEXT_KEY, whereText)
        editor.apply()
    }

    fun saveDate(context : Context, savedDate: String){
        val editor = getPreferences(context).edit()
        editor.putString(DATE_TEXT_KEY, savedDate)
        editor.apply()
    }

    fun getFromText(context: Context): String? {
        return getPreferences(context).getString(FROM_TEXT_KEY, "")
    }

    fun getWhereText(context: Context): String? {
        return getPreferences(context).getString(WHERE_TEXT_KEY, "")
    }

    fun getSavedFlightDate(context : Context):String? {
        return getPreferences(context).getString(DATE_TEXT_KEY, "")
    }
}