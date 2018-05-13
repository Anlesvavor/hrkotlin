package org.anlesvavor.factories.interfaces

import org.anlesvavor.modelos.Country

interface InterfazCountryDAO{
    fun create(obj : Country)
    fun readById(id : Long) : Country
    fun readByCriteria(criteria : String) : List<Country>
    fun update(obj : Country)
    fun delete(id : Long)
}