package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.Job

interface InterfazJobDAO {
    fun create(obj : Job)
    fun readById(id : Long) : Job
    fun readByCriteria(criteria : String) : List<Job>
    fun update(obj : Job)
    fun delete(id : Long)
}