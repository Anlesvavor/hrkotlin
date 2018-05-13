package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.JobHistory

interface InterfazJobHistoryDAO {
    fun create(obj : JobHistory)
    fun readById(id : Long) : JobHistory
    fun readByCriteria(criteria : String) : List<JobHistory>
    fun update(obj : JobHistory)
    fun delete(id : Long)
}