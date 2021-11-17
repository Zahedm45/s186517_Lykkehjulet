package com.example.s186517lykkehjulet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(
    private val wordArr: CharArray,
    private val context: Context ): RecyclerView.Adapter<WordAdapter.ViewHolder>() {


   // private val wordArr = word.toList()


    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val wordButton = view.findViewById<Button>(R.id.wordButton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curWord = wordArr[position]
        holder.wordButton.text = curWord.toString()
    }

    override fun getItemCount() = wordArr.size
}