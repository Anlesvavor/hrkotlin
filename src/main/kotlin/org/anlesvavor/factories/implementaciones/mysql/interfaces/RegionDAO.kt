package org.anlesvavor.factories.implementaciones.mysql.interfaces

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
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Region.SELECT_ALL} $criteria")

    }

    override fun update(obj: Region) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun makeRegion(rs : ResultSet) : Region = Region(rs.getLong(1), rs.getString(2))
    }
}