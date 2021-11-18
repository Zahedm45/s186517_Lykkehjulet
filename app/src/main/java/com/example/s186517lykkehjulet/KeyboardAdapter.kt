package com.example.s186517lykkehjulet

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class KeyboardAdapter(
    private val wordArr: CharArray,
    private val context: Context,
    private val board: Board,
    private val wordAdapter: WordAdapter
): RecyclerView.Adapter<KeyboardAdapter.ViewHolder>() {

    private lateinit var wordBtn : MutableList<WordButton>


    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val wordButton = view.findViewById<Button>(R.id.keyboardButton)


    }

    lateinit var view : View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view  = LayoutInflater.from(parent.context).inflate(R.layout.keyboard_button_fragment, parent, false)
        wordBtn = board.wordButton
        return ViewHolder(view)


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curWord = wordArr[position]
        holder.wordButton.text = curWord.toString()

        var isClickSucceeded = false
        holder.wordButton.setOnClickListener {
            val btnText = holder.wordButton.text.single()

            Log.i(TAG, "Clicked outside $btnText")

            for ((index, value ) in wordBtn.withIndex()) {
                Log.i(TAG, "value ${value.letter} btnText $btnText")

                if (!value.isMatched && value.letter == btnText) {
                    Log.i(TAG, "Clicked $btnText")
                    isClickSucceeded = true
                    value.isMatched = true
                    board.amountMatched += 1
                    wordAdapter.notifyChanged()
                    break
                }
            }

            // navigates to another fragment
            if (board.amountMatched == wordBtn.size) {
                Log.i(TAG, "You won the match...")
                Navigation.findNavController(view).navigate(R.id.winDisplay1)

            }

            val player = board.player
            if (!isClickSucceeded) {

                player.turns -= 1
                Log.i(TAG, "player ${player.turns}")
            }

            if (player.turns == 0) {
                Navigation.findNavController(view).navigate(R.id.lostDisplay_nav)
            }
        }

    }

    override fun getItemCount() = wordArr.size



}