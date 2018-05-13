package org.anlesvavor.modelos

import java.util.*

class Employee(
    _id: Long = 0,
    _firstName: String = "",
    _lastName: String = "",
    _email: String = "",
    _phoneNumber: String = "",
    _hireDate: Date = Date(),
    _job: Job = Job(),
    _salary: Double = 0.0,
    _commissionPct: Float = 0.0f,
    _manager: Employee = Employee(),
    _department: Department = Department()
) {
    var id : Long = _id
    var firstName : String = _firstName
    var lastName : String = _lastName
    var email : String = _email
    var phoneNumber : String = _phoneNumber
    var hireDate : Date = _hireDate
    var job : Job = _job
    var salary : Double = _salary
    var commissionPct : Float = _commissionPct
    var manager : Employee = _manager
    var department : Department = _department

    companion object Constantes {

        val FIELDS = listOf(
            "employee_id",
            "first_name",
            "last_name",
            "email",
            "phone_nmber",
            "hire_date",
            "job_id",
            "salary",
            "commission_pct",
            "manager_id",
            "department_id"
        )
        val TABLE: String = "employees"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE "
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES ${Modelo.addInterrogations(FIELDS)} "
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ?"
    }
}
