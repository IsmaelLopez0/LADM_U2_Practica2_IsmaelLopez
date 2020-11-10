package com.example.ladm_u2_practica2_ismaellopez

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.util.*

@SuppressLint("ViewConstructor")
class Lienzo(p: MainActivity): View(p) {
    private var puntero = p
    private var random = Random()
    private val cantidadMoscas = random.nextInt(80..101)
    private var restantes = cantidadMoscas
    var moscaArray = Array<Mosca>(cantidadMoscas) { Mosca(this, R.drawable.mosca32, R.drawable.manchar) }
    var tiempoRestante = 60
    private var can : Canvas ?= null
    private var p = Paint()

    override fun onDraw(c : Canvas) {
        super.onDraw(c)
        can = c

        (0 until cantidadMoscas).forEach {
            moscaArray[it].dibujar(c, p)
        }
        p.textSize = 50f
        c.drawText("Cantidad: $restantes", 50f, 50f, p)
        c.drawText("Tiempo: $tiempoRestante", 500f, 50f, p)

        if ( restantes == 0){
            puntero.hilo.detener()
            borrarMoscas()

            p.color = Color.argb(200, 255, 255, 255)
            c.drawRect(25f, 900f, 1055f, 1170f, p)

            p.color = Color.BLACK
            p.textSize = 100f
            c.drawText("¡GANASTE!", 50f, 1000f, p)

            p.textSize = 75f
            c.drawText("Acabaste con las moscas :)", 50f, 1120f, p)
        } else if ( tiempoRestante == 0 ) {
            puntero.hilo.detener()
            borrarMoscas()

            p.color = Color.argb(200, 255, 255, 255)
            c.drawRect(25f, 900f, 1055f, 1170f, p)

            p.color = Color.BLACK
            p.textSize = 100f
            c.drawText("¡Se acabó el tiempo!", 50f, 1000f, p)

            p.textSize = 75f
            c.drawText("Te quedarón $restantes moscas :(", 50f, 1120f, p)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if ( event.action == MotionEvent.ACTION_DOWN ) {
            (0 until cantidadMoscas).forEach {
                if ( moscaArray[it].estaEnArea(event.x, event.y) ) {
                    moscaArray[it].despintar(can!!, p)
                    restantes -= 1
                }
            }
        }
        return true
    }

    fun getCantidadMoscas(): Int{
        return cantidadMoscas
    }

    fun borrarMoscas(){
        (0 until cantidadMoscas).forEach {
            moscaArray[it].despintar(can!!, p)
        }
        invalidate()
    }

    private fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }
}