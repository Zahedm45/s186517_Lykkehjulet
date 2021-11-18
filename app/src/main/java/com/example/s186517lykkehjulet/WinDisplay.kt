package com.example.s186517lykkehjulet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlin.system.exitProcess

class WinDisplay : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_win_display, container, false)
        val tvPoint = view.findViewById<TextView>(R.id.pointTV_win)
        tvPoint.text = "You final total pont is ${DataPasser.playerPoints}"

        view.findViewById<Button>(R.id.playButton_lost).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.wordsReFragment)
        }

        view.findViewById<Button>(R.id.stopButton_lost).setOnClickListener {

            activity?.finish()
            exitProcess(0)
        }
        return view
    }
}