package com.example.androiddrawingpad

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androiddrawingpad.databinding.FragmentDrawingPadBinding
import kotlinx.coroutines.launch

class DrawingPadFragment : Fragment() {

    private lateinit var bindingDrawingPad: FragmentDrawingPadBinding
    private lateinit var buttonsSizeArray: Array<Button>
    private lateinit var buttonsColorArray: Array<Button>
    private var viewModel = DrawingPadViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDrawingPad = FragmentDrawingPadBinding.inflate(layoutInflater, container, false)
        //Set Coroutine to Reset and Invalidate DrawingBox
        lifecycleScope.launch{
            viewModel.resetCanvas.collect {
                if(it) {
                    bindingDrawingPad.drawingBox.resetCanvas()
                    bindingDrawingPad.drawingBox.postInvalidate()
                }
            }
        }
        return bindingDrawingPad.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Apply OnClickListeners for UI Buttons to pass needed Values to ViewModel
        bindingDrawingPad.apply {
            buttonsSizeArray = arrayOf(btnS, btnM, btnL)
            buttonsSizeArray.forEach { button ->
                button.setOnClickListener { viewModel.setSize(button, bindingDrawingPad)  }
            }
            buttonsColorArray = arrayOf(btnBlack, btnBlue, btnRed, btnGreen, btnYellow)
            buttonsColorArray.forEach { button ->
                button.setOnClickListener { viewModel.setColor(button, bindingDrawingPad) }
            }
            bindingDrawingPad.btnTrash.setOnClickListener {viewModel.clearCanvas() }
        }
    }
}