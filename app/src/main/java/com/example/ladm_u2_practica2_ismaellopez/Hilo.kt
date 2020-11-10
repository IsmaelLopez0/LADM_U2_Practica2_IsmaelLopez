package com.example.ladm_u2_practica2_ismaellopez

class Hilo(puntero: MainActivity): Thread() {
    private var mantener = true
    private val p = puntero
    private var c = 1

    override fun run() {
        super.run()
        while (mantener){
            p.runOnUiThread {
                (0 until p.lienzo!!.getCantidadMoscas()).forEach {
                    p.lienzo!!.moscaArray[it].mover()
                    p.lienzo!!.invalidate()
                }
            }
            sleep(10L)
            c += 1
            if( c == 100 ){
                c = 0
                p.lienzo!!.tiempoRestante -= 1
            }
        }
    }

    fun detener() {
        mantener = false
    }
}