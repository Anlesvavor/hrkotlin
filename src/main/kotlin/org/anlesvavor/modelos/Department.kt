package org.anlesvavor.modelos

class Department(_id: Long = 0, _name: String = "", _manager: Long = 0, _location: Location = Location()) {

    var id : Long = _id
    var name : String = _name
    var manager : Long = _manager
    var location : Location = _location

    companion object Constantes {

        val FIELDS = listOf("department_id", "department_name", "manager_id", "location_id")
        val TABLE: String = "departments"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE "
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES (${Modelo.addInterrogations(FIELDS)})"
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ?"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Department

        if (id != other.id) return false
        if (name != other.name) return false
        if (manager != other.manager) return false
        if (location != other.location) return false

        return true
    }

    override fun toString(): String {
        return "Department(id=$id, name='$name', manager=$manager, location=$location)"
    }


}