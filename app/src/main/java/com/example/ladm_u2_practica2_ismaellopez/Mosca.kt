package com.example.ladm_u2_practica2_ismaellopez

import java.util.*
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Mosca(p: Lienzo, nombreImg: Int, manchaImg: Int) {
    private var puntero = p
    private var random = Random()
    private var x = random.nextInt(60..950).toFloat()
    private var y = random.nextInt(60..1800).toFloat()
    private var img = BitmapFactory.decodeResource(puntero.resources, nombreImg)
    private var incX = 0
    private var incY = 0
    private var init = false
    private var pintar = true
    private var mancha = manchaImg

    fun dibujar(c: Canvas, p: Paint){
        c.drawBitmap(img, x, y, p)
        if (!init) {
            incX = random.nextInt(1..10)
            incY = random.nextInt(1..10)
            if (random.nextInt(1..100) % 2 == 0) {//si el número aleatorio generado es par
                incX *= -1
            } else {
                incY *= -1
            }
            init = true
        }
    }

    fun estaEnArea(posX: Float, posY: Float): Boolean {
        if ( pintar ) {
            if (posX >= x && posX <= x + img.width) {
                if (posY >= y && posY <= y + img.height) {
                    return true
                }
            }
        }
        return false
    }

    fun despintar(c: Canvas, p: Paint){
        pintar = false
        img = BitmapFactory.decodeResource(puntero.resources, mancha)
        c.drawBitmap(img, x, y, p)
    }

    fun mover(){
        if ( pintar ) {
            if (x <= 10 || x >= 1000) {
                incX *= -1
            }
            if (y <= 60 || y >= 1850) {
                incY *= -1
            }
            x += incX
            y += incY
        }
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }
}