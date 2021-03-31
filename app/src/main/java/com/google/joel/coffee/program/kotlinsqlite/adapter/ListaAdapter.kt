package com.google.joel.coffee.program.kotlinsqlite.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.joel.coffee.program.kotlinsqlite.NameActivity
import com.google.joel.coffee.program.kotlinsqlite.R
import com.google.joel.coffee.program.kotlinsqlite.db.DatabaseHandler
import com.google.joel.coffee.program.kotlinsqlite.model.Pessoa
import kotlin.reflect.KFunction1

class ListaAdapter(nameList: List<Pessoa>, internal var context: Context,
                   private val callbacks: KFunction1<Int, Unit>):
        RecyclerView.Adapter<ListaAdapter.ViewHolder>() {


    internal var nameList: List<Pessoa> = ArrayList()
    init {
        this.nameList = nameList
    }

    // Aqui é onde o viewholder é criado a partir do layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.content_list, parent, false)
        return ViewHolder(view)
    }

    // Nessa parte é onde se modifica o item do viewHolder
    override fun onBindViewHolder(holder: ListaAdapter.ViewHolder, position: Int) {
        val name = nameList[position]

        holder.name.text = name.nome
        if(position % 2 == 0) holder.name.setBackgroundColor(Color.GRAY)
        else holder.name.setBackgroundColor(Color.WHITE)
        holder.name.setOnClickListener {
            val intent = Intent(context, NameActivity::class.java)
            intent.putExtra("edit", true)
            intent.putExtra("position", name.id)
            context.startActivity(intent)
        }
        holder.btn.setOnClickListener {
            val databaseHandler = DatabaseHandler(context)
            databaseHandler.deletePessoa(name.id)
            callbacks(position)
        }
    }

    // Devolve quantidade de itens do nameList
    override fun getItemCount(): Int {
        return nameList.size
    }

    // Criação dos itens do viewHolder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.tvAdpNome)
        var btn: Button = view.findViewById(R.id.btnAdpDel)
    }


}