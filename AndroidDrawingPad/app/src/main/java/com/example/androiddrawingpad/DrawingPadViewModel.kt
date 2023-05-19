package com.example.androiddrawingpad

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.androiddrawingpad.databinding.FragmentDrawingPadBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
//
//object CurrentColor {
//    var value = "-16777216"
//}
//object CurrentSize {
//    var value = 10f
//}

class DrawingPadViewModel : ViewModel() {

    companion object {
        private var _currentSize = MutableStateFlow(10f)
        val currentSize = _currentSize.asStateFlow()
        private val _currentColor = MutableStateFlow("-16777216")
        val currentColor = _currentColor.asStateFlow()
    }
    private val _resetCanvas = MutableStateFlow(false)
    val resetCanvas = _resetCanvas.asStateFlow()


    fun setSize(button: Button, bindingDrawingPad: FragmentDrawingPadBinding) {
        when (button) {
            bindingDrawingPad.btnS -> _currentSize.tryEmit(10f)
            bindingDrawingPad.btnM -> _currentSize.tryEmit(20f)
            bindingDrawingPad.btnL -> _currentSize.tryEmit(30f)
            else -> return
        }
    }

    fun setColor(button: Button, bindingDrawingPad: FragmentDrawingPadBinding) {
        val currentBtn: Int
        when (button) {
            bindingDrawingPad.btnBlack, bindingDrawingPad.btnBlue, bindingDrawingPad.btnRed,
            bindingDrawingPad.btnGreen, bindingDrawingPad.btnYellow ->
            {
                currentBtn = (button.background as ColorDrawable).color
                _currentColor.value = currentBtn.toString()
                //CurrentColor.value = currentBtn.toString()
                Log.d("ColorValue", currentBtn.toString())
            }
            else -> _currentColor.value = (bindingDrawingPad.btnBlack.background as ColorDrawable).color.toString()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            setBtnColor(bindingDrawingPad)
//        }
        }
    }

    fun clearCanvas() {
        Log.d("TRASH", "ENTRY")
        Log.d("ValBeforeTRASH", resetCanvas.value.toString())
        _resetCanvas.tryEmit(true)

        Log.d("ValAfterTRASH", resetCanvas.value.toString())
        _resetCanvas.value = false

    }
    fun getColor(): String { return currentColor.value }
    fun getSize(): Float { return currentSize.value }
}
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun setBtnColor(bindingDrawingPad: FragmentDrawingPadBinding) {
//        val currentIntColor = Integer.parseInt(currentColor.value.toString())
//        bindingDrawingPad.btnBlack.setBackgroundColor(currentIntColor)
//        Log.d("COLOR", currentColor.value.toString())
//    }
//        val myColor = (bindingDrawingPad.btnGreen.background as ColorDrawable).color
//        val myBtnColor = bindingDrawingPad.btnGreen.background
        //ResourcesCompat.getColor(context!!.resources, R.color.black, null)
//        val myResColor = R.color.green
//        //resources.getColorStateList(R.color.black, null)
//        Log.d("SETCOLOR", currentColor.value.toString())
//        Log.d("SETCOLOR", myColor.toString())
//        Log.d("GETCOLOR", myBtnColor.toString())
//        Log.d("GETCOLOR", myResColor.toString())
//        val color = myResColor.toString()
////     val colo2= Color.parseColor(myResColor.toString())
//        Log.d("GETCOLOR", color)
