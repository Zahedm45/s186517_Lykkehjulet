package com.example.s186517lykkehjulet

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView


class WordAdapter(
    //private val wordArr: CharArray,
    private val context: Context,
    private val wordBtn: List<WordButton>
): RecyclerView.Adapter<WordAdapter.ViewHolder>() {



    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val wordButton = view.findViewById<Button>(R.id.wordButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.word, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val curWord = wordBtn[position]
        //       holder.wordButton.text = curWord.letter.toString()
        // Log.i(TAG, "curWord: index ${wordBtn.indexOf(curWord)} letter ${curWord.letter}, isFaceUp ${curWord.isFaceUp}, isMatched ${curWord.isMatched}")

        if (curWord.letter == '-') {
            // Log.i(TAG, "second value is ${curWord.letter} index is ${wordBtn.indexOf(curWord)}")
            holder.wordButton.text = "-"
//            holder.wordButton.setBackgroundColor(Color.GRAY)
            curWord.isFaceUp = true

        } else if (curWord.isMatched) {
//            Log.i(TAG, "second value is ${curWord.letter} index is ${wordBtn.indexOf(curWord)}")
            holder.wordButton.text = curWord.letter.toString()
//            holder.wordButton.setBackgroundColor(Color.GREEN)
//            holder.wordButton.setTextColor(Color.BLACK)
            curWord.isFaceUp = true
//            Log.i(TAG, "WordAdapter onBind called")
        } else {
            holder.wordButton.text = ""
        }

    }

    override fun getItemCount(): Int {
        return wordBtn.size
    }

    fun notifyChanged() {
        notifyDataSetChanged()
    }



}