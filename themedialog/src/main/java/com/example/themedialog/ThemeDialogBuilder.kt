package com.example.themedialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themedialog.databinding.DialogLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ThemeDialogBuilder(private val context: Context, private val recreate: () -> Unit) {
    private lateinit var builder: MaterialAlertDialogBuilder
    private lateinit var colorAdapter: ColorAdapter
    private val binding = DialogLayoutBinding.inflate(LayoutInflater.from(context))
    private var colorTheme: Int = Themes().getThemePosition(context)
    private var modeTheme = Themes().getModePosition(context)

    init {
        createDialog()
    }

    private fun createDialog() {
        val colorArray = listOf(
            R.color.one_seed,
            R.color.two_seed,
            R.color.analogous_two_seed,
            R.color.triadic_seed,
        )

        colorAdapter = ColorAdapter(context, colorArray, Themes().getThemePosition(context)) {
            colorTheme = it
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = colorAdapter
        }

        binding.toggleButton.check(
            when (Themes().getModePosition(context)) {
                0 -> R.id.buttonSystem
                1 -> R.id.buttonDay
                2 -> R.id.buttonNight
                else -> R.id.buttonSystem
            }
        )
        binding.toggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.buttonSystem -> {
                        modeTheme = 0
                    }

                    R.id.buttonDay -> {
                        modeTheme = 1
                    }

                    R.id.buttonNight -> {
                        modeTheme = 2
                    }
                }
            }
        }

        builder = MaterialAlertDialogBuilder(context).setView(binding.root)
        builder.setTitle(R.string.choose_theme)
            .setIcon(R.drawable.ic_round_brush)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(context.getText(R.string.ok)) { dialog, which ->
                themeAction(colorTheme)
                modeAction(modeTheme)
            }
    }

    private fun themeAction(value: Int) {
        Themes().setThemeValue(value, context)
        recreate()
    }

    private fun modeAction(value: Int) {
        Log.i("mode", value.toString())
        Themes().setModeValue(value, context)
        recreate()
    }

    fun create(): AlertDialog {
        return builder.create()
    }
}