package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.JobHistory
import java.sql.Date

interface InterfazJobHistoryDAO {
    fun create(obj : JobHistory)
    fun readById(employeeId: Long, startDate: String) : JobHistory
    fun readByCriteria(criteria : String) : List<JobHistory>
    fun update(obj : JobHistory)
    fun delete(employeeId: Long, startDate: String)
}