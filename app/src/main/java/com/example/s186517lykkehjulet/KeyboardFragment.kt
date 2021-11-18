package com.example.s186517lykkehjulet

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.s186517lykkehjulet.databinding.FragmentGamePageBinding

class KeyboardFragment : Fragment() {


     var _binding: FragmentGamePageBinding? = null
     val binding get() = _binding!!
    private lateinit var randomSelectedCategory : String



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
        val recyclerView = binding.recyclerView
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
        recyclerView.adapter = KeyboardAdapter(charAlphabetList, requireContext(), board, wordAdapter, binding)



    }









    fun getSelected(str: String) : Int {
        return when(str.replace(" ", "").lowercase()) {
            "computerbrands" -> R.array.Airlines
            "carbrands" -> R.array.Airlines
            "airlines" -> R.array.Airlines
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
