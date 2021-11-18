package com.example.s186517lykkehjulet

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s186517lykkehjulet.databinding.FragmentGamePageBinding

class KeyboardAdapter(
    private val wordArr: CharArray,
    private val context: Context,
    private val board: Board,
    private val wordAdapter: WordAdapter,
    private val binding: FragmentGamePageBinding
): RecyclerView.Adapter<KeyboardAdapter.ViewHolder>() {

    private var wordBtn = board.wordButton


    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val keyButton = view.findViewById<Button>(R.id.keyboardButton)


    }

    lateinit var view : View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view  = LayoutInflater.from(parent.context).inflate(R.layout.keyboard_button_fragment, parent, false)

        setPlayerTurnsOnDisplay(board, binding)
        return ViewHolder(view)


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curKey = wordArr[position]
        holder.keyButton.text = curKey.toString()


        holder.keyButton.setOnClickListener {
            val keyboardBtnText = holder.keyButton.text.single()
            var isClickSucceeded = false
            Log.i(TAG, "Clicked outside $keyboardBtnText")

            for ((index, value ) in wordBtn.withIndex()) {
                Log.i(TAG, "value ${value.letter} btnText $keyboardBtnText")

                if (value.letter == '-' || ((!value.isMatched) && (value.letter.equals(keyboardBtnText)) && (!value.isMatched))) {
                    Log.i(TAG, "Clicked $keyboardBtnText")
                    isClickSucceeded = true
                    value.isMatched = true
                    board.amountMatched += 1
                    wordAdapter.notifyChanged()
                    break
                }
            }

            // navigates to another fragment if there is a winner
            if (board.amountMatched == wordBtn.size) {
                Log.i(TAG, "You won the match...")
                Navigation.findNavController(view).navigate(R.id.winDisplay1)

            }

            val player = board.player

            if (!isClickSucceeded) {
               // Log.i(TAG, "player turns ${player.turns}")
                if (player.turns != 1) {
                    val alertDialog = AlertDialog.Builder(context)
                    alertDialog.setTitle("Your word does not match!")
                    alertDialog.setMessage("You lose 1 turn, you now have ${player.turns-1}")
                    alertDialog.show()
                }
                player.turns -= 1
                setPlayerTurnsOnDisplay(board, binding)

            }

            if (player.turns == 0) {
                Navigation.findNavController(view).navigate(R.id.lostDisplay_nav)
            }

        }

    }

    override fun getItemCount() = wordArr.size


    fun setPlayerTurnsOnDisplay(board: Board, binding: FragmentGamePageBinding){
       // Log.i(TAG, "Turn left called")
        val leftTurns = binding.turnLeftTv
        val player = board.player
        leftTurns.text = "Turns left: ${player.turns}"

    }



}