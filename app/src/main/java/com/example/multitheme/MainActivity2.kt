package com.example.multitheme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.multitheme.databinding.ActivityMain2Binding
import com.example.themedialog.Themes

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Themes().modeAndColor(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }
}