package org.anlesvavor.modelos

class Location {

    var id : Long
    var streetAddress : String
    var postalCode : Long
    var city : String
    var stateProvince : String
    var country : Country

    constructor(
        _id : Long = 0,
        _streetAddress : String = "",
        _postalCode : Long = 0,
        _city : String = "",
        _stateProvince : String = "",
        _country : Country = Country()
    ) {
        id = _id
        streetAddress =_streetAddress
        postalCode = _postalCode
        city = _city
        stateProvince = _stateProvince
        country = _country
    }

    companion object Constantes {

        val FIELDS = listOf("location_id" ,"street_address", "postal_code", "city", "state_province", "country_id")
        val TABLE: String = "locations"
        val SELECT_ALL: String = "SELECT ${Modelo.makeFields(FIELDS)} FROM $TABLE "
        val SELECT_BY_ID: String = "$SELECT_ALL WHERE ${FIELDS[0]} = ?"
        val INSERT: String = "INSERT INTO $TABLE (${Modelo.makeFields(FIELDS)}) VALUES (${Modelo.addInterrogations(FIELDS)})"
        val UPDATE: String = "UPDATE $TABLE SET ${Modelo.makeUpdateFields(FIELDS)} WHERE ${FIELDS[0]} = ?"
        val DELETE: String = "DELETE FROM $TABLE WHERE ${FIELDS[0]} = ?"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (id != other.id) return false
        if (streetAddress != other.streetAddress) return false
        if (postalCode != other.postalCode) return false
        if (city != other.city) return false
        if (stateProvince != other.stateProvince) return false
        if (country != other.country) return false

        return true
    }

    override fun toString(): String {
        return "Location(id=$id, streetAddress='$streetAddress', postalCode=$postalCode, city='$city', stateProvince='$stateProvince', country=$country)"
    }


}