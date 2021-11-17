package com.example.s186517lykkehjulet

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class KeyboardAdapter(
    private val wordArr: CharArray,
    private val context: Context,
    private val wordBtn: MutableList<WordButton>
): RecyclerView.Adapter<KeyboardAdapter.ViewHolder>() {


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
        holder.wordButton.setOnClickListener {
            val btnText = holder.wordButton.text.single()
            val newBtn = WordButton(btnText)

            for ((index, value ) in wordBtn.withIndex()) {
                if (!value.isMatched && value.letter.equals(btnText)) {
                    Log.i(TAG, "helllll")
                    value.isMatched = true
                    break
                }
            }

/*            if (wordBtn.contains(newBtn)) {
                val pos: Int = wordBtn.indexOf(newBtn)


                wordBtn.remove( newBtn)
                Log.i(TAG, "helllll")
            }*/
        }

    }

    override fun getItemCount() = wordArr.size



}