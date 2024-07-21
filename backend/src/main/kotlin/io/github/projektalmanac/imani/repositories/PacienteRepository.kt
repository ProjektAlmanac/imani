package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.Paciente
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PacienteRepository : CrudRepository<Paciente, Int>
