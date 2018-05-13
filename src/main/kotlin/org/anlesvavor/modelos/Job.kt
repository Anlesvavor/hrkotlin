package org.anlesvavor.modelos

class Job(
    _id: Long = 0,
    _title: String = "",
    _minSalary: Double = 0.0,
    _maxSalary: Double = 0.0
) {
    var id : Long = _id
    var title : String = _title
    var minSalary : Double = _minSalary
    var maxSalary : Double = _maxSalary

    companion object Constantes {

        val FIELDS = listOf("job_id", "job_title", "min_salary", "max_salary")
        val TABLE: String = "jobs"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE"
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES "
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ?"
    }
}
