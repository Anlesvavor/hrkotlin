package org.anlesvavor.modelos

import java.time.Instant
import java.util.*

class JobHistory(
    _employee: Employee = Employee(),
    _startDate: String = "",
    _endDate: String =  "",
    _job: Job = Job(),
    _department: Department = Department()
) {
    var employee : Employee = _employee
    var startDate : String = _startDate
    var endDate : String = _endDate
    var job : Job = _job
    var department : Department = _department

    companion object Constantes {

        val FIELDS = listOf("employee_id", "start_date", "end_date", "job_id", "department_id")
        val TABLE: String = "job_history"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE "
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ? AND ${FIELDS[1]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES (${Modelo.addInterrogations(FIELDS)})"
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ? AND ${FIELDS[1]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ? AND ${FIELDS[1]} = ?"
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JobHistory

        if (employee != other.employee) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false
        if (job != other.job) return false
        if (department != other.department) return false

        return true
    }


    override fun toString(): String {
        return "JobHistory(employee=$employee, startDate='$startDate', endDate='$endDate', job=$job, department=$department)"
    }
}