package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazRegionDAO
import org.anlesvavor.modelos.Region
import org.junit.Assert
import org.junit.Test

class RegionDAOTest {

    val region : Region = Region(2 , "Europa")
    val regionDAO : InterfazRegionDAO = RegionDAO()

    @Test
    fun CreateRegionDAOTest() {
        regionDAO.create(region)
        Assert.assertEquals(regionDAO.readById(region.id!!), region)
        println(regionDAO.readById(region.id!!))
        regionDAO.delete(region.id!!)
    }

    @Test
    fun readRegionByIdDAOTest(){
        val prueba = regionDAO.readById(1)
        Assert.assertNotNull(prueba)
        println(prueba)
    }

    @Test
    fun readByCriteriaRegionDAOTest(){
        val criteria : String = "WHERE region_name = 'America'"
        println("${Region.SELECT_ALL} $criteria")
        val prueba = regionDAO.readByCriteria("WHERE region_name = 'America'")
        Assert.assertNotNull(prueba)
        println(prueba)
    }

    @Test
    fun UpdateregionDAOTest() {
        val regionInicial = Region(2, "")
        regionDAO.create(regionInicial)
        println("region inicial: $regionInicial")
        val regionUpdate = Region(2, "Oceania")
        regionDAO.update(regionUpdate)
        val prueba = regionDAO.readById(2)
        Assert.assertEquals(regionUpdate, prueba)
        println(prueba)
        regionDAO.delete(2)
    }

    @Test
    fun DeleteRegionDAOTest() {
        val regionInicial = Region(100, "El ombligo del mundo: Armezlario")
        val regionVacia = Region()
        regionDAO.create(regionInicial)
        println("region inicial: $regionInicial")
        regionDAO.delete(100)
        Assert.assertEquals(regionDAO.readById(100), regionVacia)
    }
}