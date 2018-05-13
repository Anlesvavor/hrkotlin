package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.Location

interface InterfazLocationDAO {
    fun create(obj : Location)
    fun readById(id : Long) : Location
    fun readByCriteria(criteria : String) : List<Location>
    fun update(obj : Location)
    fun delete(id : Long)
}