package com.example.androiddrawingpad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.androiddrawingpad.databinding.FragmentIntroScreenBinding
import kotlinx.coroutines.launch

class IntroScreenFragment : Fragment() {

    private lateinit var bindingIntroScreen: FragmentIntroScreenBinding
    private var viewModel = IntroScreenViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel
        bindingIntroScreen = FragmentIntroScreenBinding.inflate(layoutInflater, container, false)
        lifecycleScope.launch {
            viewModel.animationEnded.collect {
                val fragmentManager = requireActivity().supportFragmentManager
                if(it) { viewModel.setNewFrag(fragmentManager) }
                else { return@collect }
            }
        }
        return bindingIntroScreen.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingIntroScreen.btnStart.setOnClickListener {
            viewModel.setAnimation(bindingIntroScreen)
        }
    }
}