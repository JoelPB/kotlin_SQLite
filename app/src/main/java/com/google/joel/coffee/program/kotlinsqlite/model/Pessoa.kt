package com.google.joel.coffee.program.kotlinsqlite.model

data class Pessoa (
        var id: Int = 0,
        var nome: String = "",
        var sobrenome: String = "",
        var endereco: String = "",
        var telefone: String = "",
//        var foto: String = ""
)