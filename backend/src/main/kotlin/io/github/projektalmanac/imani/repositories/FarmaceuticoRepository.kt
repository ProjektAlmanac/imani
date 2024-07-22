package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.Farmaceutico
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FarmaceuticoRepository: CrudRepository<Farmaceutico, Int> {
    fun findFarmaceuticoByNombreUsuario(nombreUsuario: String): Farmaceutico?
}
