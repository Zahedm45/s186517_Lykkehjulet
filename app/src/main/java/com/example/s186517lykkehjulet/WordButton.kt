package com.example.s186517lykkehjulet

import android.widget.Button

data class WordButton(
    val letter: Char,
    var isFaceUp : Boolean = false,
    var isMatched : Boolean = false
)
