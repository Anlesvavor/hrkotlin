package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazCountryDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.factories.interfaces.InterfazRegionDAO
import org.anlesvavor.modelos.Country
import org.anlesvavor.modelos.Location
import java.sql.ResultSet

class LocationDAO(_countryDAO : InterfazCountryDAO = CountryDAO()) : InterfazLocationDAO{

    var countryDAO : InterfazCountryDAO = _countryDAO


    override fun create(obj: Location) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Location.INSERT)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.streetAddress)
        ps.setLong(i++, obj.postalCode)
        ps.setString(i++, obj.city)
        ps.setString(i++, obj.stateProvince)
        ps.setLong(i, obj.country.id)
        ps.executeUpdate()
    }

    override fun readById(id: Long): Location {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Location.SELECT_BY_ID)
        var location = Location()
        ps.setLong(1, id)
        val rs = ps.executeQuery()
        if (rs.next()) {
            location = makeLocation(rs)
        }
        return location
    }

    override fun readByCriteria(criteria: String): List<Location> {
        var locations : MutableList<Location> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Location.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            locations.add(makeLocation(rs))
        }
        return locations
    }

    override fun update(obj: Location) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Location.UPDATE)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.streetAddress)
        ps.setLong(i++, obj.postalCode)
        ps.setString(i++, obj.city)
        ps.setString(i++, obj.stateProvince)
        ps.setLong(i, obj.country.id)
        // el id del where
        ps.setLong(i, obj.id!!)
        ps.executeUpdate()
    }

    override fun delete(id: Long) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Location.DELETE)
        ps.setLong(1, id)
        ps.executeUpdate()
    }

    private fun makeLocation(rs : ResultSet) : Location =
        Location(
            rs.getLong(1),
            rs.getString(2),
            rs.getLong(3),
            rs.getString(4),
            rs.getString(5),
            countryDAO.readById(rs.getLong(6))
        )
}