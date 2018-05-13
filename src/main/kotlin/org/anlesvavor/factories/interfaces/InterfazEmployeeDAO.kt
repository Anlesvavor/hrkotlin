package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.Employee

interface InterfazEmployeeDAO {
    fun create(obj : Employee)
    fun readById(id : Long) : Employee
    fun readByCriteria(criteria : String) : List<Employee>
    fun update(obj : Employee)
    fun delete(id : Long)
}