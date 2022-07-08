package com.example.margemnokotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var change: Button
    private lateinit var grow: Button
    private lateinit var shrink: Button
    private lateinit var up: Button
    private lateinit var down: Button
    private lateinit var text: TextView
    var above = false
    var marginTimer: CountDownTimer? = null
    var sizeTimer: CountDownTimer? = null
    var counter = 0
    var bottom = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        change = findViewById<Button>(R.id.change)
        up = findViewById<Button>(R.id.up)
        down = findViewById<Button>(R.id.down)
        grow = findViewById<Button>(R.id.grow)
        shrink = findViewById<Button>(R.id.shrink)
        text = findViewById<TextView>(R.id.text)

        Toast.makeText(
            applicationContext,
            "Tap the buttons to change layout attributes!",
            Toast.LENGTH_LONG
        ).show()

        change.setOnClickListener {
            val params = text.layoutParams as RelativeLayout.LayoutParams
            if (above) {
                params.removeRule(RelativeLayout.ABOVE)
                params.addRule(RelativeLayout.BELOW, change.id)
            } else {
                params.removeRule(RelativeLayout.BELOW)
                params.addRule(RelativeLayout.ABOVE, change.id)
            }
            counter = 1
            //Resetting original margin Bottom
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottom)
            text.layoutParams = params
            above = !above
        }

        counter = 1
        //Getting the initial margin Bottom
        //Getting the initial margin Bottom
        bottom = (text.layoutParams as RelativeLayout.LayoutParams).bottomMargin
        val marginListener =
            View.OnClickListener { view ->
                if (marginTimer != null) marginTimer!!.cancel()
                marginTimer = object : CountDownTimer(800, 50) {
                    override fun onTick(l: Long) {
                        if (view.id == up.id) {
                            counter++
                            val params = text.layoutParams as RelativeLayout.LayoutParams
                            params.setMargins(
                                params.leftMargin,
                                params.topMargin,
                                params.rightMargin,
                                bottom * counter
                            )
                            //Resetting the TextView to above the button
                            params.removeRule(RelativeLayout.BELOW)
                            params.addRule(RelativeLayout.ABOVE, change.id)
                            text.layoutParams = params
                        } else {
                            counter--
                            val params = text.layoutParams as RelativeLayout.LayoutParams
                            params.setMargins(
                                params.leftMargin,
                                params.topMargin,
                                params.rightMargin,
                                bottom * counter
                            )
                            //Resetting the TextView to above the button
                            params.removeRule(RelativeLayout.BELOW)
                            params.addRule(RelativeLayout.ABOVE, change.id)
                            text.layoutParams = params
                        }
                    }

                    override fun onFinish() {}
                }.start()
            }
        up.setOnClickListener(marginListener)
        down.setOnClickListener(marginListener)

        val sizeListener =
            View.OnClickListener { view ->
                if (sizeTimer != null) sizeTimer!!.cancel()
                sizeTimer = object : CountDownTimer(1800, 100) {
                    override fun onTick(l: Long) {
                        val params = text.layoutParams
                        if (params.width <= 0) {
                            params.width = 400
                            params.height = 200
                        }
//                        else {
//                            if (view.id == R.id.grow) {
//                                (params.width *= 1).toInt()
//                                (params.height *= 1).toInt()
//                            } else {
//                                (params.width /= 1).toInt()
//                                (params.height /= 1).toInt()
//                            }
//                        }
                        text.layoutParams = params
                    }

                    override fun onFinish() {}
                }.start()
            }

        grow.setOnClickListener(sizeListener)
        shrink.setOnClickListener(sizeListener)
    }
}
