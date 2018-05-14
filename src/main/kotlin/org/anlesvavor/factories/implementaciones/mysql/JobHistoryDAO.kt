package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazDepartmentDAO
import org.anlesvavor.factories.interfaces.InterfazJobDAO
import org.anlesvavor.factories.interfaces.InterfazJobHistoryDAO
import org.anlesvavor.modelos.Department
import org.anlesvavor.modelos.JobHistory
import java.sql.Date
import java.sql.ResultSet

class JobHistoryDAO(_departmentDAO: DepartmentDAO = DepartmentDAO(), _jobDAO: InterfazJobDAO = JobDAO()) : InterfazJobHistoryDAO{
    var departmentDAO : InterfazDepartmentDAO = _departmentDAO
    var jobDAO : InterfazJobDAO = _jobDAO
    //var employeeDAO : EmployeeDAO = _employeeDAO

    override fun create(obj: JobHistory) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(JobHistory.INSERT)
        var i = 1
        ps.setLong(i++, obj.employee.id)
        ps.setString(i++, obj.startDate )
        ps.setString(i++, obj.endDate )
        ps.setLong(i++, obj.job.id)
        ps.setLong(i, obj.department.id)
        ps.executeUpdate()
    }

    override fun readById(employeeId: Long, startDate: String): JobHistory {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(JobHistory.SELECT_BY_ID)
        var jobHistory = JobHistory()
        ps.setLong(1, employeeId)
        ps.setString(2, startDate)
        val rs = ps.executeQuery()
        if (rs.next()) {
            jobHistory = makeJobHistory(rs)
        }
        return jobHistory
    }

    override fun readByCriteria(criteria: String): List<JobHistory> {
        var jobHistories : MutableList<JobHistory> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${JobHistory.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            jobHistories.add(makeJobHistory(rs))
        }
        return jobHistories
    }

    override fun update(obj: JobHistory) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(JobHistory.UPDATE)
        var i = 1
        ps.setLong(i++, obj.employee.id)
        ps.setString(i++, obj.startDate )
        ps.setString(i++, obj.endDate )
        ps.setLong(i++, obj.job.id)
        ps.setLong(i++, obj.department.id)
        // el id del where
        ps.setLong(i++, obj.job.id)
        ps.setString(i, obj.startDate)
        ps.executeUpdate()
    }

    override fun delete(employeeId: Long, startDate: String) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(JobHistory.DELETE)
        ps.setLong(1, employeeId)
        ps.setString(2, startDate)
        ps.executeUpdate()
    }

    private fun makeJobHistory(rs : ResultSet) : JobHistory =
        JobHistory(
            EmployeeDAO.readById(rs.getLong(1)),
            rs.getString(2),
            rs.getString(3),
            jobDAO.readById(rs.getLong(4)),
            departmentDAO.readById(rs.getLong(5))
        )
}