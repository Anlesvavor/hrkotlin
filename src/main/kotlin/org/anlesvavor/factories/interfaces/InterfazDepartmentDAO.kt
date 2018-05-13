package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.Department

interface InterfazDepartmentDAO {
    fun create(obj : Department)
    fun readById(id : Long) : Department
    fun readByCriteria(criteria : String) : List<Department>
    fun update(obj : Department)
    fun delete(id : Long)
}