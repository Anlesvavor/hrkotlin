package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazDepartmentDAO
import org.anlesvavor.factories.interfaces.InterfazEmployeeDAO
import org.anlesvavor.factories.interfaces.InterfazJobDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.modelos.*
import org.junit.Assert
import org.junit.Test
import java.time.Instant
import java.util.*

class EmployeeDAOTest {

    val departmentDAO : InterfazDepartmentDAO = DepartmentDAO()
    val locationDAO : InterfazLocationDAO = LocationDAO()
    val jobDAO : InterfazJobDAO = JobDAO()

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


    @Test
    fun createEmployeeDAO(){
        EmployeeDAO.delete(juan.id)
        EmployeeDAO.create(juan)
        val employeeVacio = Employee()
        Assert.assertNotEquals(locationDAO.readById(juan.id),  employeeVacio)
        locationDAO.delete(juan.id)
    }

    @Test
    fun readByIdEmployeeDAO(){
        departmentDAO.delete(sistemas.id)
        jobDAO.delete(desarrollador.id)
        locationDAO.delete(location.id)

        locationDAO.create(location)
        departmentDAO.create(sistemas)
        jobDAO.create(desarrollador)
        EmployeeDAO.delete(juan.id)
        EmployeeDAO.create(juan)
        val esperado = EmployeeDAO.readById(juan.id)
        Assert.assertEquals(juan.toString(), esperado.toString())
        println(esperado)
        EmployeeDAO.delete(juan.id)
        jobDAO.delete(desarrollador.id)
        departmentDAO.delete(sistemas.id)
        locationDAO.delete(location.id)
    }

    @Test
    fun readByCriteriaEmployeeDAO(){
        EmployeeDAO.delete(juan.id)
        EmployeeDAO.create(juan)
        val prueba = EmployeeDAO.readByCriteria("WHERE first_name = 'Juan'")
        val employeeVacio = Employee()
        Assert.assertNotEquals(prueba, employeeVacio)
        EmployeeDAO.delete(juan.id)
    }

    @Test
    fun UpdateEmployeeDAO(){
        departmentDAO.delete(sistemas.id)
        jobDAO.delete(desarrollador.id)
        locationDAO.delete(location.id)

        locationDAO.create(location)
        departmentDAO.create(sistemas)
        jobDAO.create(desarrollador)
        EmployeeDAO.delete(juan.id)
        EmployeeDAO.create(juan)
        val updatedEmployee = pepe
        EmployeeDAO.update(pepe) // pepe tiene el mismo id que juan
        Assert.assertEquals(EmployeeDAO.readById(1).toString(), updatedEmployee.toString())
        jobDAO.delete(desarrollador.id)
        departmentDAO.delete(sistemas.id)
        locationDAO.delete(location.id)
    }

    @Test
    fun DeleteEmployeeDAO(){
        EmployeeDAO.delete(juan.id)
        EmployeeDAO.create(juan)
        EmployeeDAO.delete(juan.id)
        Assert.assertEquals(locationDAO.readById(location.id), Location()) //Borrado
    }

}