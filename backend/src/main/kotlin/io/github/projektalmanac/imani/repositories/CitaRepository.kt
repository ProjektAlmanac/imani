package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.Cita
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CitaRepository : CrudRepository<Cita, Int>
