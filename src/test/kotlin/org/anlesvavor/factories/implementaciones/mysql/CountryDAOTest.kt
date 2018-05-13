package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazCountryDAO
import org.anlesvavor.factories.interfaces.InterfazRegionDAO
import org.anlesvavor.modelos.Country
import org.anlesvavor.modelos.Region
import org.junit.Assert
import org.junit.Test

class CountryDAOTest {

    val countryDAO : InterfazCountryDAO = CountryDAO()
    val regionDAO : InterfazRegionDAO = RegionDAO()
    val region : Region = regionDAO.readById(1)
    val country : Country =  Country(1, "Mexico", region)

    @Test
    fun createCountryDAO(){
        countryDAO.delete(country.id)
        countryDAO.create(country)
        val countryVacio = Region(0, "")
        Assert.assertNotEquals(countryDAO.readById(country.id), countryVacio)
        countryDAO.delete(country.id)
    }

    @Test
    fun readByIdCountryDAO(){
        countryDAO.delete(country.id)
        countryDAO.create(country)
        val esperado = countryDAO.readById(country.id)
        Assert.assertEquals(country, esperado)
        println(esperado)
        countryDAO.delete(country.id)
    }

    @Test
    fun readByCriteriaCountryDAO(){
        countryDAO.delete(country.id)
        countryDAO.create(country)
        val prueba = countryDAO.readByCriteria("WHERE country_name = 'Mexico'")
        val countryVacio = Region(0, "")
        Assert.assertNotEquals(prueba, countryVacio)
        countryDAO.delete(country.id)
    }

    @Test
    fun UpdateCountryDAO(){
        countryDAO.delete(country.id)
        countryDAO.create(country)
        val updatedCountry = Country(1, "Madagascar", region)
        countryDAO.update(Country(1, "Madagascar", region))
        Assert.assertEquals(countryDAO.readById(1), updatedCountry)
        countryDAO.delete(country.id)
    }

    @Test
    fun DeleteCountryDAO(){
        countryDAO.delete(country.id)
        countryDAO.create(country)
        countryDAO.delete(country.id)
        Assert.assertEquals(countryDAO.readById(country.id), Country(0,"")) //Borrado
    }
}