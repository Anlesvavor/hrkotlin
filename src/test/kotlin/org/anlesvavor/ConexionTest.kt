package org.anlesvavor

import org.anlesvavor.connections.Conexion
import org.junit.Assert
import org.junit.Test

class ConexionTest {

    @Test
    fun lasConsultasDebenDeEstarFuncionando() {
        val con = Conexion
        val ps = con.getConexion()!!.prepareStatement("SELECT region_id, region_name FROM regions")
        val rs = ps.executeQuery()
        rs.next()
        Assert.assertNotNull(rs.getString(2))
        println(rs.getString(2))
    }



}