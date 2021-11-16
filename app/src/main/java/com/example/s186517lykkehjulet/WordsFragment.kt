package com.example.s186517lykkehjulet

import WordAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s186517lykkehjulet.databinding.FragmentWordsBinding

class WordsFragment : Fragment() {


    private var _binding: FragmentWordsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      // val view = inflater.inflate(R.layout.fragment_words, container, false)

        _binding = FragmentWordsBinding.inflate(inflater, container, false)
        val view = binding.root

        val categoryList = resources.getStringArray(R.array.categoriesArray)
        val categoryArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_list_fragment, categoryList)
        binding.ACTextView.setAdapter(categoryArrayAdapter)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
         // recyclerView.layoutManager = LinearLayoutManager(requireContext())
         recyclerView.layoutManager = GridLayoutManager(requireContext(), 12)

        recyclerView.adapter = WordAdapter(requireContext(), "b")
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//        )

        val list = resources.getStringArray(R.array.categoriesArray).toList()
    }
}