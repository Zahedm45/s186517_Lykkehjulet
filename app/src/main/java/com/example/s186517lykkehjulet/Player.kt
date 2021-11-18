package com.example.s186517lykkehjulet

data class Player(
    var points : Int = 0,
    var isWon : Boolean = false,
    var turns : Int = 5,
    var spinWheelValue : String = "Null"
)
