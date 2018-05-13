package org.anlesvavor.modelos

class Country(_id: Long = 0, _name: String = "", _region: Region = Region()) {

    var id : Long = _id
    var name : String = _name
    var region: Region = _region

    companion object Constantes {

        val FIELDS = listOf("country_id", "country_name, region_id")
        val TABLE: String = "regions"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE"
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES "
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ?"
    }
}