package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazCountryDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.factories.interfaces.InterfazRegionDAO
import org.anlesvavor.modelos.Country
import org.anlesvavor.modelos.Location
import org.anlesvavor.modelos.Region
import org.junit.Assert
import org.junit.Test

class LocationDAOTest{
    val locationDAO : InterfazLocationDAO = LocationDAO()
    val countryDAO : InterfazCountryDAO = CountryDAO()
    val country : Country = countryDAO.readById(1)
    val location : Location =  Location(1, "5 de mayo",33000, "Chihuahua", "Chihuahua", country)


    @Test
    fun createLocationDAO(){
        locationDAO.delete(location.id)
        locationDAO.create(location)
        val locationVacio = Country()
        Assert.assertNotEquals(locationDAO.readById(location.id), locationVacio)
        locationDAO.delete(location.id)
    }

    @Test
    fun readByIdLocationDAO(){
        locationDAO.delete(location.id)
        locationDAO.create(location)
        val esperado = locationDAO.readById(location.id)
        Assert.assertEquals(location, esperado)
        println(esperado)
        locationDAO.delete(location.id)
    }

    @Test
    fun readByCriteriaLocationDAO(){
        locationDAO.delete(location.id)
        locationDAO.create(location)
        val prueba = locationDAO.readByCriteria("WHERE location_id = 'Mexico'")
        val locationVacio = Location()
        Assert.assertNotEquals(prueba, locationVacio)
        locationDAO.delete(location.id)
    }

    @Test
    fun UpdateLocationDAO(){
        locationDAO.delete(location.id)
        locationDAO.create(location)
        val updatedLocation = Location(1, "Madagascar", 2, "city", "state", country)
        locationDAO.update(Location(1, "Madagascar",  2, "city", "state", country))
        Assert.assertEquals(locationDAO.readById(1), updatedLocation)
        locationDAO.delete(location.id)
    }

    @Test
    fun DeleteLocationDAO(){
        locationDAO.delete(location.id)
        locationDAO.create(location)
        locationDAO.delete(location.id)
        Assert.assertEquals(locationDAO.readById(location.id), Location()) //Borrado
    }
}