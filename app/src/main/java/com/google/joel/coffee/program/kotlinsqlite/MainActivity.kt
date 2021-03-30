package com.google.joel.coffee.program.kotlinsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.joel.coffee.program.kotlinsqlite.adapter.ListaAdapter
import com.google.joel.coffee.program.kotlinsqlite.databinding.ActivityMainBinding
import com.google.joel.coffee.program.kotlinsqlite.db.DatabaseHandler
import com.google.joel.coffee.program.kotlinsqlite.model.Pessoa

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Iniciar a RecyclerView
    lateinit var listaAdapter: ListaAdapter //= null
    var linearLayoutManager: LinearLayoutManager? = null

    // SQLite
    var pessoaList = ArrayList<Pessoa>()
    var databaseHandler = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.btnInsert.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivityForResult(intent,1)
        }

    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {
        pessoaList = databaseHandler.pessoas()
        listaAdapter = ListaAdapter(pessoaList, this, this::deleteAdapter)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = listaAdapter
    }

    private fun deleteAdapter(position: Int){
        pessoaList.removeAt(position)
        listaAdapter.notifyItemRemoved(position)
    }
}