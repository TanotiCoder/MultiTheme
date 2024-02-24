package com.example.themedialog

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class Themes {
    private fun setTheme(key: String, value: Int, context: Context) {
        val preferences: SharedPreferences = context.getSharedPreferences(
            "preferences",
            Context.MODE_PRIVATE
        )
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun getTheme(context: Context, key: String): Int {
        val preferences: SharedPreferences = context.getSharedPreferences(
            "preferences",
            Context.MODE_PRIVATE
        )
        return preferences.getInt(key, 0)
    }


    private fun setMode(key: String, value: Int, context: Context) {
        setTheme(key, value, context)
    }

    private fun getMode(context: Context, key: String): Int {
        return getTheme(context, key)
    }

    fun modeAndColor(context: Context) {
        mode(context)
        color(context)
    }

    fun color(context: Context) {
        when (getTheme(context, "Color")) {
            0 -> context.setTheme(R.style.ThemeOne)
            1 -> context.setTheme(R.style.ThemeTwo)
            2 -> context.setTheme(R.style.AnalogousTwoTheme)
            3 -> context.setTheme(R.style.TriadicTheme)
        }
    }

     private fun mode(context: Context) {

        when (getMode(context, "Mode")) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }


    fun setThemeValue(value: Int, context: Context, key: String = "Color") {
        setTheme(key, value, context)
    }

    fun getThemePosition(context: Context) = getTheme(context, "Color")

    fun setModeValue(value: Int, context: Context, key: String = "Mode") {
        setMode(key, value, context)
    }

    fun getModePosition(context: Context) = getMode(context, "Mode")

}