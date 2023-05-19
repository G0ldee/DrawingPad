package com.example.androiddrawingpad

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.androiddrawingpad.DrawingPadViewModel.Companion.currentColor
import com.example.androiddrawingpad.DrawingPadViewModel.Companion.currentSize

class DrawingBoxView(
    context: Context,
    attrs: AttributeSet? = null):
    View(context, attrs) {

    private var drawingPadViewModel = DrawingPadViewModel()
    private var paint = Paint()
    private var currentBrush = Color.BLACK
    private var currentBrushSize = 10f

    companion object {
        var path = Path()
        var arrPath = ArrayList<Path>()
        var arrPaint = ArrayList<Int>()
        var arrSize = ArrayList<Float>()
    }

    init {
        path = Path()
        paint = Paint()
        paint.color = currentBrush
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        for (i in arrPath.indices) {
            paint.color = arrPaint[i]
            paint.strokeWidth = arrSize[i]
            canvas.drawPath(arrPath[i], paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val posX: Float = event!!.x
        val posY: Float = event.y
        //Set CurrentColor CompanionObj to clicked color choice
        findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
            currentColor.collect {
                currentBrush = Integer.parseInt(drawingPadViewModel.getColor())
                //Log.d("LifeCycleColor", currentBrush.toString())
            }
        }
        //Set CurrentSize CompanionObj to clicked size choice
        findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
            currentSize.collect {
                currentBrushSize = drawingPadViewModel.getSize()
                //Log.d("LifeCycleSize", currentBrushSize.toString())
            }
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(posX, posY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(posX, posY)
                arrPath.add(path)
                arrPaint.add(currentBrush)
                arrSize.add(currentBrushSize)
            }
            MotionEvent.ACTION_UP -> { path = Path()
            }
            else -> return false
        }
        invalidate()
        return true
    }

    //reset ArrayList and path
    fun resetCanvas() {
        Log.d("BeforeRESETCANVAS", arrPaint.toString())
        arrPath.clear()
        arrPaint.clear()
        arrSize.clear()
        path.reset()
        Log.d("AfterRESETCANVAS", arrPaint.toString())
    }
}
