package com.google.joel.coffee.program.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.joel.coffee.program.kotlinsqlite.databinding.ActivityNameBinding
import com.google.joel.coffee.program.kotlinsqlite.db.DatabaseHandler
import com.google.joel.coffee.program.kotlinsqlite.model.Pessoa

class NameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNameBinding

    // Database
    val databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_name)
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edit = intent.getBooleanExtra("edit", false)
        val position = intent.getIntExtra("position", 0)

        if(edit){
            val pessoa = databaseHandler.getPessoa(position)
            binding.edtNome.setText(pessoa.nome)
            binding.btnInsertNome.setText("Editar")
        }

        binding.btnInsertNome.setOnClickListener{
            if (binding.edtNome.text.toString() == ""){
                Toast.makeText(this, "Nome está vazio.", Toast.LENGTH_SHORT).show()
            }
            else {
                if (edit){
                    val pessoa = Pessoa(position, binding.edtNome.text.toString())
                    databaseHandler.updatePessoa(pessoa)
                    finish()
                }
                else {
                    val pessoa = Pessoa(0, binding.edtNome.text.toString())
                    databaseHandler.addPessoa(pessoa)
                    finish()
                }
            }

        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}