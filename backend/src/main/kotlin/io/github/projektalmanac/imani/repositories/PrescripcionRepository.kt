package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.Prescripcion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PrescripcionRepository: CrudRepository<Prescripcion, Int>
