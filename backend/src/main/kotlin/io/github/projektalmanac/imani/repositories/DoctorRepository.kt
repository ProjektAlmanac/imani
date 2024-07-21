package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.Doctor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository : CrudRepository<Doctor, Int>
