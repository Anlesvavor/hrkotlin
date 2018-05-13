package org.anlesvavor.connections

import java.sql.*
import java.sql.DriverManager.*

object Conexion {

    var conn: Connection? = null
    init {
        createConexion()
    }

    fun getConexion() : Connection?  = conn

    fun createConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hr3?useSSL=false&serverTimezone=UTC",
                "hr3",
                "hr3"
            )
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
