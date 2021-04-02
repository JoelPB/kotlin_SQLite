package com.google.joel.coffee.program.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.joel.coffee.program.kotlinsqlite.databinding.ActivityAdressBinding

class AdressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}