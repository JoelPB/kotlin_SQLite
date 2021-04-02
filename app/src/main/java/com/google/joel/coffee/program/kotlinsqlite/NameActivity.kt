package com.google.joel.coffee.program.kotlinsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        binding = ActivityNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editPessoa()
//
//        val edit = intent.getBooleanExtra("edit", false)
//        val position = intent.getIntExtra("position", 0)
//
//        if(edit){
//            val pessoa = databaseHandler.getPessoa(position)
//            binding.edtNome.setText(pessoa.nome)
//            binding.btnInsertNome.setText("Editar")
////            binding.btnDados.visibility = View.VISIBLE
//        }
////        else {
////            binding.btnDados.visibility = View.GONE
////        }
//
//        btnEventClick(edit, position)

    }

    private fun editPessoa() {
        val edit = intent.getBooleanExtra("edit", false)
        val position = intent.getIntExtra("position", 0)

        if(edit){
            val pessoa = databaseHandler.getPessoa(position)
            binding.edtNome.setText(pessoa.nome)
            binding.edtSobrenome.setText(pessoa.sobrenome)
            binding.edtEndereco.setText(pessoa.endereco)
            binding.edtTelefone.setText(pessoa.telefone)
            binding.btnInsertNome.setText("Editar")
        }

        btnEventClick(edit, position)
    }

    private fun btnEventClick(edit: Boolean, position: Int) {
        binding.btnInsertNome.setOnClickListener{

            if (binding.edtNome.text.toString() == ""){
                Toast.makeText(this, "Nome está vazio.", Toast.LENGTH_SHORT).show()
            }
            else {
                if (edit){
                    val pessoa = Pessoa(position, binding.edtNome.text.toString(),
                            binding.edtSobrenome.text.toString(),
                            binding.edtEndereco.text.toString(),
                            binding.edtTelefone.text.toString().replace(
                                    "([0-9]{2})([0-9]{4,5})([0-9]{4})".toRegex(),
                                    "($1) $2-$3"
                            )
                    /*site: https://cursos.alura.com.br/forum/topico-regex-em-kotlin-63810
                    val padraoEsperado = "([0-9]{2})([0-9]{4,5})([0-9]{4})".toRegex()
                    val formatoEsperado = "($1) $2-$3"
                    val telefoneFormatado = "1122223333".replace(padraoEsperado, formatoEsperado)
                     */
                    )
                    databaseHandler.updatePessoa(pessoa)
                    finish()
                }
                else {
                    val pessoa = Pessoa(0, binding.edtNome.text.toString(),
                            binding.edtSobrenome.text.toString(),
                            binding.edtEndereco.text.toString(),
                            binding.edtTelefone.text.toString().replace(
                                    "([0-9]{2})([0-9]{4,5})([0-9]{4})".toRegex(),
                                    "($1) $2-$3"
                            )
                    )
                    databaseHandler.addPessoa(pessoa)
                    finish()
                }
            }

        }

//        binding.btnDados.setOnClickListener {
//            val intent = Intent(this, AdressActivity::class.java)
//            startActivityForResult(intent, 1)
//        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}