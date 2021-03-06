package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazDepartmentDAO
import org.anlesvavor.factories.interfaces.InterfazEmployeeDAO
import org.anlesvavor.factories.interfaces.InterfazJobDAO
import org.anlesvavor.factories.interfaces.InterfazLocationDAO
import org.anlesvavor.modelos.Department
import org.anlesvavor.modelos.Employee
import java.sql.Date
import java.sql.ResultSet

object EmployeeDAO: InterfazEmployeeDAO {

    /**
     * Se hace esta clase en objeto con el fin de que los metodos sean "estaticos" para
     * poder usarlo en DepartmentDAO con el fin de quitar la propiedad
     * EmployeeDAO y evitar una referencia circular que al final provocará
     * un java.lang.StackOverflowError
     */

    /**
     * declarar un Objeto es declarar un Singleton en Kotlin
     */

    //var jobDAO : InterfazJobDAO = JobDAO()

    override fun create(obj: Employee) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Employee.INSERT)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.firstName)
        ps.setString(i++, obj.lastName)
        ps.setString(i++, obj.email)
        ps.setString(i++, obj.phoneNumber)
        ps.setString(i++, obj.hireDate)
        ps.setLong(i++, obj.job.id)
        ps.setDouble(i++, obj.salary)
        ps.setFloat(i++, obj.commissionPct)
        ps.setLong(i++, obj.managerId)
        ps.setLong(i, obj.department.id)
        ps.executeUpdate()
    }

    override fun readById(id: Long): Employee {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Employee.SELECT_BY_ID)
        var employee = Employee()
        ps.setLong(1, id)
        val rs = ps.executeQuery()
        if (rs.next()) {
            employee = makeEmployee(rs)
        }
        return employee
    }

    override fun readByCriteria(criteria: String): List<Employee> {
        var employees : MutableList<Employee> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Employee.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            employees.add(makeEmployee(rs))
        }
        return employees
    }

    override fun update(obj: Employee) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Employee.UPDATE)
        var i = 1
        ps.setLong(i++, obj.id!!)
        ps.setString(i++, obj.firstName)
        ps.setString(i++, obj.lastName)
        ps.setString(i++, obj.email)
        ps.setString(i++, obj.phoneNumber)
        ps.setString(i++, obj.hireDate)
        ps.setLong(i++, obj.job.id)
        ps.setDouble(i++, obj.salary)
        ps.setFloat(i++, obj.commissionPct)
        ps.setLong(i++, obj.managerId)
        ps.setLong(i++, obj.department.id)
        // el id del where
        ps.setLong(i, obj.id!!)
        ps.executeUpdate()
    }

    override fun delete(id: Long) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Employee.DELETE)
        ps.setLong(1, id)
        ps.executeUpdate()
    }

    private fun makeEmployee(rs : ResultSet) : Employee =
        Employee(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            JobDAO.readById(rs.getLong(7)),
            rs.getDouble(8),
            rs.getFloat(9),
            rs.getLong(10),
            DepartmentDAO.readById(rs.getLong(11)) //raedById "estático"
        )


    /*
    companion object {
        val jobDAO : JobDAO = JobDAO()
        val departmentDAO : DepartmentDAO = DepartmentDAO()

        fun staticReadById(id : Long) : Employee {
            val c = Conexion.getConexion()
            val ps = c!!.prepareStatement(Employee.SELECT_BY_ID)
            var employee = Employee()
            ps.setLong(1, id)
            val rs = ps.executeQuery()
            if (rs.next()) {
                employee = makeEmployee(rs)
            }
            return employee
        }


        private fun makeEmployee(rs : ResultSet) : Employee =
            Employee(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getDate(6),
                jobDAO.readById(rs.getLong(7)),
                rs.getDouble(8),
                rs.getFloat(9),
                rs.getLong(10),
                departmentDAO.readById(rs.getLong(11))
            )
    }
    */
}