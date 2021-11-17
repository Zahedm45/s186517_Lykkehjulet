package com.example.s186517lykkehjulet

data class Board (
    var wordButton: MutableList<WordButton>,
    var amountMatched : Int = 0
)