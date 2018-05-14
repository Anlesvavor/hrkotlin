package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazDepartmentDAO
import org.anlesvavor.factories.interfaces.InterfazEmployeeDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.modelos.Department
import java.sql.ResultSet

class DepartmentDAO(
    _locationDAO: InterfazLocationDAO = LocationDAO()

)  : InterfazDepartmentDAO{

    var locationDAO : InterfazLocationDAO = _locationDAO
    //var employeeDAO : InterfazEmployeeDAO = _employeeDAO


    override fun create(obj: Department) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Department.INSERT)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.name)
        ps.setLong(i++, obj.manager)
        ps.setLong(i, obj.location.id)
        ps.executeUpdate()
    }

    override fun readById(id: Long): Department {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Department.SELECT_BY_ID)
        var department = Department()
        ps.setLong(1, id)
        val rs = ps.executeQuery()
        if (rs.next()) {
            department = makeDepartment(rs)
        }
        return department
    }

    override fun readByCriteria(criteria: String): List<Department> {
        var departments : MutableList<Department> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Department.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            departments.add(makeDepartment(rs))
        }
        return departments
    }

    override fun update(obj: Department) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Department.UPDATE)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.name)
        ps.setLong(i++, obj.manager)
        ps.setLong(i++, obj.location.id)
        // el id del where
        ps.setLong(i, obj.id!!)
        ps.executeUpdate()
    }

    override fun delete(id: Long) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Department.DELETE)
        ps.setLong(1, id)
        ps.executeUpdate()
    }

    private fun makeDepartment(rs : ResultSet) : Department =
        Department(
            rs.getLong(1),
            rs.getString(2),
            rs.getLong(3),
            locationDAO.readById(rs.getLong(4))
        )

    companion object {

        val locationDAO : LocationDAO = LocationDAO()

        fun readById(id: Long): Department {
            val c = Conexion.getConexion()
            val ps = c!!.prepareStatement(Department.SELECT_BY_ID)
            var department = Department()
            ps.setLong(1, id)
            val rs = ps.executeQuery()
            if (rs.next()) {
                department = makeDepartment(rs)
            }
            return department
        }

        private fun makeDepartment(rs : ResultSet) : Department =
            Department(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                locationDAO.readById(rs.getLong(4))
            )
    }
}