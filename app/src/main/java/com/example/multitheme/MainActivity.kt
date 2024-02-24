package com.example.multitheme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.multitheme.databinding.ActivityMainBinding
import com.example.themedialog.ThemeChooserDialogBuilder
import com.example.themedialog.Themes


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Themes().modeAndColor(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = binding.changeTheme

        button.setOnClickListener {
            ThemeChooserDialogBuilder(this) {
                recreate()
            }
                .create()
                .show()
        }

    }
}