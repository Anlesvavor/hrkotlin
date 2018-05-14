package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazDepartmentDAO
import org.anlesvavor.factories.interfaces.InterfazJobDAO
import org.anlesvavor.factories.interfaces.InterfazJobHistoryDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.modelos.*
import org.junit.Assert
import org.junit.Test
import java.time.Instant
import java.util.*

class JobHistoryTest {

    val departmentDAO : InterfazDepartmentDAO = DepartmentDAO()
    val locationDAO : InterfazLocationDAO = LocationDAO()
    val jobDAO : InterfazJobDAO = JobDAO()
    val jobHistoryDAO : InterfazJobHistoryDAO = JobHistoryDAO()

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
    val jobHistory : JobHistory = JobHistory(
        juan,
        Date.from(Instant.now()).toString(),
        Date.from(Instant.now()).toString(),
        desarrollador,
        sistemas
        )

    val jobHistory2 : JobHistory = JobHistory(
        juan,
        Date.from(Instant.now()).toString(),
        Date.from(Instant.now()).toString(),
        desarrollador,
        Department(2, "IT", 1, location)
    )

    @Test
    fun createJobHistoryDAO(){
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
        jobHistoryDAO.create(jobHistory)
        val jobHistoryVacio = JobHistory()
        Assert.assertNotEquals(jobHistoryDAO.readById(jobHistory.employee.id, jobHistory.startDate),  jobHistoryVacio)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
    }

    @Test
    fun readByIdEmployeeDAO(){
        departmentDAO.delete(sistemas.id)
        jobDAO.delete(desarrollador.id)
        locationDAO.delete(location.id)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)

        locationDAO.create(location)
        departmentDAO.create(sistemas)
        jobDAO.create(desarrollador)
        EmployeeDAO.delete(juan.id)
        EmployeeDAO.create(juan)
        val esperado = jobHistoryDAO.readById(jobHistory.employee.id, jobHistory.startDate)
        Assert.assertEquals(jobHistory.toString(), esperado.toString())
        println(esperado)
        jobDAO.delete(desarrollador.id)
        departmentDAO.delete(sistemas.id)
        locationDAO.delete(location.id)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
    }

    @Test
    fun readByCriteriaJobHistoryDAO(){
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
        jobHistoryDAO.create(jobHistory)
        val prueba = jobHistoryDAO.readByCriteria("WHERE employee_id = 1 AND department_id = 1")
        Assert.assertNotNull(prueba)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
    }

    @Test
    fun UpdateJobHistoryDAO(){
        departmentDAO.delete(sistemas.id)
        jobDAO.delete(desarrollador.id)
        locationDAO.delete(location.id)

        locationDAO.create(location)
        departmentDAO.create(sistemas)
        jobDAO.create(desarrollador)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
        jobHistoryDAO.create(jobHistory)
        val updatedJobHistory = jobHistory2
        jobHistoryDAO.update(jobHistory2)
        Assert.assertEquals(2,  jobHistoryDAO.readById(jobHistory2.employee.id, jobHistory.startDate).department.id)
        jobDAO.delete(desarrollador.id)
        departmentDAO.delete(sistemas.id)
        locationDAO.delete(location.id)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
    }

    @Test
    fun DeleteJobHistoryDAO(){
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
        jobHistoryDAO.create(jobHistory)
        jobHistoryDAO.delete(jobHistory.employee.id, jobHistory.startDate)
        Assert.assertEquals(jobHistoryDAO.readById(jobHistory.employee.id, jobHistory.startDate), JobHistory()) //Borrado
    }

}