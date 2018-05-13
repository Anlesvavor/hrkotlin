package org.anlesvavor.modelos

class Region(_id: Long = 0, _name: String = "") {

    var id : Long? = _id
    var name : String = _name

    companion object Constantes {

        val FIELDS = listOf("region_id", "region_name")
        val TABLE: String = "regions"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE "
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES (${Modelo.addInterrogations(FIELDS)})"
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ?"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Region

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun toString(): String {
        return "Region(id=$id, name='$name')"
    }

}