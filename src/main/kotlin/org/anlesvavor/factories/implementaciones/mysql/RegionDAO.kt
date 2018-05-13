package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazRegionDAO
import org.anlesvavor.modelos.Region
import java.sql.ResultSet

class RegionDAO : InterfazRegionDAO{

    override fun create(obj: Region) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Region.INSERT)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i, obj.name)
        ps.executeUpdate()
    }

    override fun readById(id: Long): Region {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Region.SELECT_BY_ID)
        var region = Region()
        ps.setLong(1, id)
        val rs = ps.executeQuery()
        if (rs.next()) {
            region = makeRegion(rs)
        }
        return region
    }

    override fun readByCriteria(criteria: String): List<Region> {
        var regiones : MutableList<Region> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Region.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            regiones.add(makeRegion(rs))
        }
        return regiones
    }

    override fun update(obj: Region) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Region.UPDATE)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.name)
        // el id del where
        ps.setLong(i, obj.id!!)
        ps.executeUpdate()
    }

    override fun delete(id: Long) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Region.DELETE)
        ps.setLong(1, id)
        ps.executeUpdate()
    }

    private fun makeRegion(rs : ResultSet) : Region = Region(rs.getLong(1), rs.getString(2))
}