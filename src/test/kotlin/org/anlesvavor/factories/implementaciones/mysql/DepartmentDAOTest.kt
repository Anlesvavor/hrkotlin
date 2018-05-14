package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazCountryDAO
import org.anlesvavor.factories.interfaces.InterfazDepartmentDAO
import org.anlesvavor.factories.interfaces.InterfazEmployeeDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.modelos.*
import org.junit.Assert
import org.junit.Test
import java.time.Instant
import java.util.*

class DepartmentDAOTest{

    val departmentDAO : InterfazDepartmentDAO = DepartmentDAO()
    val locationDAO : InterfazLocationDAO = LocationDAO()



    val location : Location =  Location(1, "5 de mayo",33000, "Chihuahua", "Chihuahua", Country())
    val desarrollador : Job = Job(1, "Desarrollador", 5000.0, 35000.0)
    val sistemas : Department = Department(1, "Sistemas", 1, location)
    val juan : Employee = Employee(1,
        "Juan",
        "Tuercas",
        "juan@tuercas.org",
        "6391503539",
        Date.from(Instant.now()).toString(),
        desarrollador,
        100000.0,
        0.01f,
        1,
        sistemas
    )
    val pepe : Employee = Employee(1,
        "Pepe",
        "Tuercas",
        "pepe@tuercas.org",
        "6391503535",
        Date.from(Instant.now()).toString(),
        desarrollador,
        100000.0,
        0.01f,
        1,
        sistemas
    )


    @Test
    fun createDepartmentDAO(){
        departmentDAO.delete(sistemas.id)
        departmentDAO.create(sistemas)
        val departmentVacio = Department()
        Assert.assertNotEquals(locationDAO.readById(sistemas.id),  departmentVacio)
        locationDAO.delete(sistemas.id)
    }

    @Test
    fun readByIdDepartmentDAO(){
        locationDAO.create(location)
        departmentDAO.delete(sistemas.id)
        departmentDAO.create(sistemas)
        val esperado = departmentDAO.readById(sistemas.id)
        Assert.assertEquals(sistemas, esperado)
        println(esperado)
        departmentDAO.delete(sistemas.id)
        locationDAO.delete(location.id)
    }

    @Test
    fun readByCriteriaDepartmentDAO(){
        departmentDAO.delete(sistemas.id)
        departmentDAO.create(sistemas)
        val prueba = departmentDAO.readByCriteria("WHERE department_name = 'Sistemas'")
        val departmentVacio = Department()
        Assert.assertNotEquals(prueba, departmentVacio)
        departmentDAO.delete(sistemas.id)
    }

    @Test
    fun UpdateDepartmentDAO(){
        departmentDAO.delete(sistemas.id)
        departmentDAO.create(sistemas)
        val updatedDepartment = Department(1, "Madagascar", 1, Location())
        departmentDAO.update(Department(1, "Madagascar", 1, Location()))
        Assert.assertEquals(departmentDAO.readById(1), updatedDepartment)
        departmentDAO.delete(sistemas.id)
    }

    @Test
    fun DeleteDepartmentDAO(){
        departmentDAO.delete(sistemas.id)
        departmentDAO.create(sistemas)
        departmentDAO.delete(sistemas.id)
        Assert.assertEquals(locationDAO.readById(location.id), Location()) //Borrado
    }

}