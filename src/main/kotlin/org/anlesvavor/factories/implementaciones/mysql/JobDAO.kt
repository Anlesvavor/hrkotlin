package org.anlesvavor.factories.implementaciones.mysql

import org.anlesvavor.connections.Conexion
import org.anlesvavor.factories.interfaces.InterfazJobDAO
import org.anlesvavor.modelos.Job
import java.sql.ResultSet

class JobDAO : InterfazJobDAO {
    override fun create(obj: Job) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Job.INSERT)
        var i = 1
        ps.setLong(i++, obj.id)
        ps.setString(i++, obj.title)
        ps.setDouble(i++, obj.minSalary)
        ps.setDouble(i++, obj.maxSalary)
        ps.executeUpdate()
    }

    override fun readById(id: Long): Job {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Job.SELECT_BY_ID)
        var job = Job()
        ps.setLong(1, id)
        val rs = ps.executeQuery()
        if (rs.next()) {
            job = makeJob(rs)
        }
        return job
    }

    override fun readByCriteria(criteria: String): List<Job> {
        var jobs : MutableList<Job> = mutableListOf()
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement("${Job.SELECT_ALL}$criteria")
        val rs = ps.executeQuery()
        while (rs.next()) {
            jobs.add(makeJob(rs))
        }
        return jobs
    }

    override fun update(obj: Job) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Job.UPDATE)
        var i = 1
        ps.setLong(i++, obj.id)
        ps.setString(i++, obj.title)
        ps.setDouble(i++, obj.minSalary)
        ps.setDouble(i++, obj.maxSalary)
        // el id del where
        ps.setLong(i, obj.id)
        ps.executeUpdate()
    }

    override fun delete(id: Long) {
        val c = Conexion.getConexion()
        val ps = c!!.prepareStatement(Job.DELETE)
        ps.setLong(1, id)
        ps.executeUpdate()
    }

    private fun makeJob(rs : ResultSet) : Job = Job(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4))

    companion object {

        fun readById(id: Long): Job {
            val c = Conexion.getConexion()
            val ps = c!!.prepareStatement(Job.SELECT_BY_ID)
            var job = Job()
            ps.setLong(1, id)
            val rs = ps.executeQuery()
            if (rs.next()) {
                job = makeJob(rs)
            }
            return job
        }

        private fun makeJob(rs : ResultSet) : Job = Job(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4))

    }
}