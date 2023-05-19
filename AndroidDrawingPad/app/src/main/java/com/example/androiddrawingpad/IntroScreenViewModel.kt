package com.example.androiddrawingpad

import android.animation.ObjectAnimator
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddrawingpad.databinding.FragmentIntroScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IntroScreenViewModel : ViewModel() {

    private val _animationEnded = MutableStateFlow(false)
    val animationEnded = _animationEnded.asStateFlow()

    fun setAnimation(bindingIntroScreen: FragmentIntroScreenBinding) = viewModelScope.launch {
        val textStart = bindingIntroScreen.textView.left.toFloat()
        val textEnd = bindingIntroScreen.textView.right.toFloat()
        val heightAnimator = ObjectAnimator
            .ofFloat(bindingIntroScreen.textView, "x", textStart, textEnd)
            .setDuration(1500L)
        heightAnimator.start()
        delay(1500L)
        _animationEnded.value = true
    }

    fun setNewFrag(fragmentManager: FragmentManager) {
        val drawingPad = DrawingPadFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, drawingPad)
            .addToBackStack(null)
            .commit()
        return
    }
}