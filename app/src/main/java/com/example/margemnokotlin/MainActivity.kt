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

    /* Declaração de Variáveis de Escopo global: */
    private lateinit var up: Button
    private lateinit var down: Button
    private lateinit var text: TextView
    var marginTimer: CountDownTimer? = null
    var counter = 0
    var bottom = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instância de Variáveis de Escopo global: */
        up = findViewById<Button>(R.id.up)
        down = findViewById<Button>(R.id.down)
        text = findViewById<TextView>(R.id.txtVw_id)

        Toast.makeText(
            applicationContext,
            "Tap the buttons to change layout attributes!",
            Toast.LENGTH_LONG
        ).show()

        counter = 1
        //Getting the initial margin Bottom
        //Getting the initial margin Bottom
        bottom = (text.layoutParams as RelativeLayout.LayoutParams).bottomMargin
        val marginListener =
            View.OnClickListener { view ->
                if (marginTimer != null) marginTimer!!.cancel()
                marginTimer = object : CountDownTimer(100, 50) {
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
                            // params.addRule(RelativeLayout.ABOVE, change.id)
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
                            //  params.addRule(RelativeLayout.ABOVE, change.id)
                            text.layoutParams = params
                        }
                    }
                    override fun onFinish() {}
                }.start()
            }
        up.setOnClickListener(marginListener)
        down.setOnClickListener(marginListener)
    }
}
