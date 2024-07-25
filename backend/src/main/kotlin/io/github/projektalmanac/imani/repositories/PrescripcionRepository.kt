package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.Prescripcion
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PrescripcionRepository : CrudRepository<Prescripcion, Int> {
    fun findAllByPacienteId(idPaciente: Int): List<Prescripcion>?
    fun findPrescripcionById(idPrescripcion: Int): Prescripcion?
}