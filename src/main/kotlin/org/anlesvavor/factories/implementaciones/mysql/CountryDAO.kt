package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazCountryDAO
import org.anlesvavor.factories.interfaces.InterfazRegionDAO
import org.anlesvavor.modelos.Country
import org.anlesvavor.modelos.Region
import java.sql.ResultSet

class CountryDAO(_regionDAO: RegionDAO = RegionDAO()) : InterfazCountryDAO {

    var regionDAO : InterfazRegionDAO = _regionDAO


    override fun create(obj: Country) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Country.INSERT)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.name)
        ps.setLong(i, obj.region.id!!)
        ps.executeUpdate()
    }

    override fun readById(id: Long): Country {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Country.SELECT_BY_ID)
        var country = Country()
        ps.setLong(1, id)
        val rs = ps.executeQuery()
        if (rs.next()) {
            country = makeCountry(rs)
        }
        return country
    }

    override fun readByCriteria(criteria: String): List<Country> {
        var countries : MutableList<Country> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Country.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            countries.add(makeCountry(rs))
        }
        return countries
    }

    override fun update(obj: Country) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Country.UPDATE)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.name)
        ps.setLong(i++, obj.region.id!!)
        // el id del where
        ps.setLong(i, obj.id!!)
        ps.executeUpdate()
    }

    override fun delete(id: Long) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Country.DELETE)
        ps.setLong(1, id)
        ps.executeUpdate()
    }

    private fun makeCountry(rs : ResultSet) : Country =
        Country(
            rs.getLong(1), rs.getString(2), regionDAO.readById(rs.getLong(3))
        )
}