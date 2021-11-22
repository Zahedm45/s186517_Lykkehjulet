package com.example.s186517lykkehjulet

import android.app.AlertDialog
import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s186517lykkehjulet.databinding.FragmentGamePageBinding

class KeyboardFragment : Fragment() {


     var _binding: FragmentGamePageBinding? = null
     val binding get() = _binding!!
    private lateinit var randomSelectedCategory : String
    private lateinit var recyclerView : RecyclerView
    private lateinit var keyboardAdapter : KeyboardAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      // val view = inflater.inflate(R.layout.fragment_words, container, false)
        _binding = FragmentGamePageBinding.inflate(inflater, container, false)
        val view = binding.root




        randomSelectedCategory = getRandomCategory().uppercase()
        binding.textCategoryView.text = randomSelectedCategory

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



// adapter for keyboard
        val alphabetList = resources.getString(R.string.alphabet)
        val charAlphabetList = alphabetList.toCharArray()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 10)




// adapter for words
        val wordBtn: MutableList<WordButton> = ArrayList()
        val d = getSelected(randomSelectedCategory)
        val wordList = resources.getStringArray(d).toList()
            .shuffled()
            .take(1)
            .toString().replace("[","").replace("]", "")

        val charArr: CharArray = wordList.toCharArray()
        for ((index, value ) in charArr.withIndex()) {
            wordBtn.add(WordButton(value))

        }



        val wordRecyclerView = binding.rvWord
        wordRecyclerView.layoutManager = GridLayoutManager(requireContext(), 12)


        var wordAdapter : WordAdapter = WordAdapter( requireContext(), wordBtn)
        wordRecyclerView.adapter = wordAdapter

        val player = Player()
        var board: Board = Board(wordBtn, player)
        spinWheel(board)
        keyboardAdapter = KeyboardAdapter(charAlphabetList, requireContext(), board, wordAdapter, binding)
        recyclerView.adapter = keyboardAdapter




    }


    private fun spinWheel(board: Board) {
        val spinBtn = binding.spinWheelBtn
        spinBtn.setOnClickListener{
            if (board.isProcessDone) {
                board.isProcessDone =  false
                val valueOption = resources.getStringArray(R.array.valueOption).toList()
                    .shuffled().shuffled()
                    .take(1)
                    .toString().replace("[","")
                    .replace("]", "")
                val str = valueOption.uppercase()
                binding.pointsTextView.text = str
                board.player.spinWheelValue = str
                // Log.i(TAG, "player spin value....................${board.player.spinWheelValue}")

                when(str.lowercase()){
                    ValueOption.BANKRUPT -> bankrupt(board)
                    ValueOption.TURN_LOST -> turnLost(board)
                    ValueOption.EXTRA_TURN -> extraTurn(board)
                    else -> {
                        binding.tvInstruction.text = "Guess a word"
                        binding.tvScreenMain.setBackgroundColor(Color.parseColor("#8fbc8f"))
                            }
                   // else -> wonPoints(board, str.lowercase())
                }
                if (board.player.turns == 0) {
                    Navigation.findNavController(requireView()).navigate(R.id.nov_lost_display)
                }

            } else {
                val tvInstruction = binding.tvInstruction
                tvInstruction.text = "Guess a word first!"
            }
        }

    }







    fun getSelected(str: String) : Int {
        return when(str.replace(" ", "").lowercase()) {
            "computerbrands" -> R.array.ComputerBrands
            "carbrands" -> R.array.CarBrands
            "airlines" -> R.array.Airlines
            "thesevenwonders" -> R.array.SevenWonders
            else -> {
                Log.i(ContentValues.TAG, "Category $str not found")
                0
            }
        }
    }

    fun getRandomCategory() : String {
        val item =requireContext().resources.getStringArray(R.array.categoryArray).toList()
        .shuffled()
        .shuffled()
        .take(1)

        return item.toString().replace("[","").replace("]", "")
    }



    private fun bankrupt(board: Board) {
        val player = board.player

        if (player.points != 0) {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("You land on bankrupt!")
            alertDialog.setMessage("You lose all of your money. No worries, keep playing..")
            alertDialog.setPositiveButton("OK",{dialog, which ->})
            alertDialog.show()
        }

        player.points = 0
        keyboardAdapter.displayPlayerPoint()
        board.isProcessDone = true
        binding.tvScreenMain.setBackgroundColor(Color.parseColor("#ff7f50"))

    }

    private fun turnLost(board: Board) {
        val player = board.player
        if (player.turns > 0) {
            player.turns -= 1
            keyboardAdapter.setPlayerTurnsOnDisplay(board, binding)
        }
        board.isProcessDone = true
        binding.tvScreenMain.setBackgroundColor(Color.parseColor("#ff7f50"))
    }

    private fun extraTurn(board: Board) {
        val player = board.player
        player.turns += 1
        keyboardAdapter.setPlayerTurnsOnDisplay(board, binding)
        board.isProcessDone = true
        binding.tvScreenMain.setBackgroundColor(Color.parseColor("#8fbc8f"))    }

    private fun wonPoints(board: Board, spinValue: String) {
        val player = board.player
        val spinValueInt = spinValue.toIntOrNull()
        if (spinValueInt != null) {
            player.points += spinValueInt
        }
    }








    // drop down items
/*        val categoryList = resources.getStringArray(R.array.categoryArray)
        val categoryArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_list_fragment, categoryList)
        binding.ACTextView.setAdapter(categoryArrayAdapter)

        var selectedItem: String
        binding.ACTextView.setOnItemClickListener {parentFragment, view, position, id ->
             selectedItem = parentFragment.getItemAtPosition(position) as String
             selectedItem = selectedItem.replace("\\s".toRegex(), "")

            Log.i(TAG,"helooohkjlkd $selectedItem")
        }*/


}
