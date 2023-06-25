package com.example.a3pagini
import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi

class MainActivity : ComponentActivity(), View.OnTouchListener {

    // Declaring ImageView, Bitmap, Canvas, Paint,
    // Down Coordinates and Up Coordinates
    private lateinit var ghidUAIC: GhidUAIC
    private lateinit var mImgZonaTraseu: ImageView
    private lateinit var canvas: Canvas



    private lateinit var bitmap: Bitmap
    private lateinit var paint: Paint
    private var downX = 0f
    private var downY = 0f
    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initializing the ImageView
        mImgZonaTraseu = findViewById(R.id.imgZonaTraseu)
        // Getting the current window dimensions
        val currentDisplay = windowManager.currentWindowMetrics
        val dw = currentDisplay.bounds.width()
        val dh = currentDisplay.bounds.height()
        ghidUAIC= GhidUAIC(mImgZonaTraseu,dw,dh)


        ghidUAIC.calculeazaTraseu(1,12)
        ghidUAIC.deseneaza()

        // Setam onTouchListener pentru ImageView
        mImgZonaTraseu.setOnTouchListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                ghidUAIC.mutaSursa(event.x,event.y)
                ghidUAIC.deseneaza()
            }

            MotionEvent.ACTION_UP -> {
                /*upX = event.x
                upY = event.y

                ghidUAIC.deseneaza()
                ghidUAIC.deseneazaLinie(downX, downY, upX, upY)
                canvas.drawLine(downX, downY, upX, upY, paint)
                mImgZonaTraseu.invalidate()*/
            }
        }
        return true
    }
}