package org.anlesvavor.modelos

import org.junit.Assert
import org.junit.Test

class Modelos2Test {


    @Test
    fun testInterrogations() {
        val lista : List<String> = listOf("1","2","3","4","5")
        val resultado = Modelo.addInterrogations(lista)
        val esperado = "?, ?, ?, ?, ?"
        Assert.assertEquals(resultado, esperado)
        println(resultado)

    }

}