package com.example.s186517lykkehjulet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.s186517lykkehjulet.databinding.FragmentGamePageBinding

class KeyboardFragment : Fragment() {


    private var _binding: FragmentGamePageBinding? = null
    private val binding get() = _binding!!
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






// adapter for keyboard
        val alphabetList = resources.getString(R.string.alphabet)
        val charAlphabetList = alphabetList.toCharArray()
        val recyclerView = binding.recyclerView
         recyclerView.layoutManager = GridLayoutManager(requireContext(), 12)
        recyclerView.adapter = KeyboardAdapter(charAlphabetList, requireContext())



// adapter for words
        val d = getSelected(randomSelectedCategory)
        val wordList = resources.getStringArray(d).toList()
            .shuffled()
            .take(1)
            .toString().replace("[","").replace("]", "")

        val charArr: CharArray = wordList.toCharArray()
        val wordRecyclerView = binding.rvWord
        wordRecyclerView.layoutManager = GridLayoutManager(requireContext(), 12)
        wordRecyclerView.adapter = WordAdapter(charArr, requireContext())

//        recyclerView.addItemDecoration(
//            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//        )
    }

    fun getSelected(str: String) : Int {
      when(str.replace(" ", "").lowercase()) {
          "computerbrand" -> return R.array.CarBrands
          "carbrands" -> return R.array.CarBrands
          "airlines" -> return R.array.Airlines
          else -> return 0
      }
    }

    fun getRandomCategory() : String {
        val item =requireContext().resources.getStringArray(R.array.categoryArray).toList()
        .shuffled()
        .shuffled()
        .take(1)

        return item.toString().replace("[","").replace("]", "")
    }



}