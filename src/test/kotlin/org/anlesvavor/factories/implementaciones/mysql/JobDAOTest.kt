package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.factories.interfaces.InterfazCountryDAO
import org.anlesvavor.factories.interfaces.InterfazJobDAO
import org.anlesvavor.modelos.Country
import org.anlesvavor.modelos.Job
import org.junit.Assert
import org.junit.Test

class JobDAOTest{
    
    val jobDAO : InterfazJobDAO = JobDAO()
    val job : Job =  Job(1, "desarrollador", 10000.0, 30000.0)


    @Test
    fun createJobDAO(){
        jobDAO.delete(job.id)
        jobDAO.create(job)
        val jobVacio = Country()
        Assert.assertNotEquals(jobDAO.readById(job.id), jobVacio)
        jobDAO.delete(job.id)
    }

    @Test
    fun readByIdJobDAO(){
        jobDAO.delete(job.id)
        jobDAO.create(job)
        val esperado = jobDAO.readById(job.id)
        Assert.assertEquals(job, esperado)
        println(esperado)
        jobDAO.delete(job.id)
    }

    @Test
    fun readByCriteriaJobDAO(){
        jobDAO.delete(job.id)
        jobDAO.create(job)
        val prueba = jobDAO.readByCriteria("WHERE job_id = 'Mexico'")
        val jobVacio = Job()
        Assert.assertNotEquals(prueba, jobVacio)
        jobDAO.delete(job.id)
    }

    @Test
    fun UpdateJobDAO(){
        jobDAO.delete(job.id)
        jobDAO.create(job)
        val updatedJob = Job(1, "diseñador", 6000.0, 340000.0)
        jobDAO.update(Job(1, "diseñador", 6000.0, 340000.0))
        Assert.assertEquals(jobDAO.readById(1), updatedJob)
        jobDAO.delete(job.id)
    }

    @Test
    fun DeleteJobDAO(){
        jobDAO.delete(job.id)
        jobDAO.create(job)
        jobDAO.delete(job.id)
        Assert.assertEquals(jobDAO.readById(job.id), Job()) //Borrado
    }
}