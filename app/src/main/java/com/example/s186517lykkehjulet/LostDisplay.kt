package com.example.s186517lykkehjulet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlin.system.exitProcess

class LostDisplay : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.lost_display_fragemnt, container, false)


        view.findViewById<Button>(R.id.playButton_lost).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_lost_display_to_main)
        }

        view.findViewById<Button>(R.id.stopButton_lost).setOnClickListener {

            activity?.finish()
            exitProcess(0)
        }
        return view
    }
}