package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.Region

interface InterfazRegionDAO {
    fun create(obj : Region)
    fun readById(id : Long) : Region
    fun readByCriteria(criteria : String) : List<Region>
    fun update(obj : Region)
    fun delete(id : Long)
}