package com.example.s186517lykkehjulet

import KeyboardAdapter
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.s186517lykkehjulet.databinding.FragmentGamePageBinding

class KeyboardFragment : Fragment() {


    private var _binding: FragmentGamePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      // val view = inflater.inflate(R.layout.fragment_words, container, false)

        _binding = FragmentGamePageBinding.inflate(inflater, container, false)
        val view = binding.root



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







        val alphabetList = resources.getStringArray(R.array.alphabet).toList()
        val recyclerView = binding.recyclerView
         recyclerView.layoutManager = GridLayoutManager(requireContext(), 12)

 //       recyclerView.adapter = KeyboardAdapter(requireContext(), "b")

        recyclerView.adapter = WordAdapter(alphabetList, requireContext())


        val wordList = listOf("m","a","c")
        val wordRecyclerView = binding.rvWord
        wordRecyclerView.layoutManager = GridLayoutManager(requireContext(), 12)
        wordRecyclerView.adapter = WordAdapter(wordList, requireContext())



//        recyclerView.addItemDecoration(
//            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//        )




    }




}