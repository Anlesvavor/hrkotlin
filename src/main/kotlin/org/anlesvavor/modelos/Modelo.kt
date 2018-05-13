package org.anlesvavor.modelos

object Modelo{

    fun makeUpdateFields(list : List<String>) : String {
        var fields : String = ""
        for (i in list.indices ){
            fields = if (i == 0) "${list[i]} = ?" else {
                var s = "$fields, ${list[i]} = ?"
                s
            }
        }
        return fields
    }

    fun makeFields(list : List<String>) : String {
        var fields : String = ""
        for (i in list.indices ){
            fields = if (i == 0) list[i] else {
                "$fields, ${list[i]}"
            }
        }
        return fields
    }


}