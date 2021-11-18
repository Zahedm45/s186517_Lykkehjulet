package com.example.s186517lykkehjulet

data class Board (
    var wordButton: MutableList<WordButton>,
    var player : Player,
    var amountMatched : Int = 0

)