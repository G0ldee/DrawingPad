package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class InfoFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textInfo: TextView = view.findViewById(R.id.info)
        val btnOk: Button = view.findViewById(R.id.btnok)

        textInfo.text = getString(R.string.beginText)

        btnOk.setOnClickListener{
            Toast.makeText(context, "Dismissed", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}