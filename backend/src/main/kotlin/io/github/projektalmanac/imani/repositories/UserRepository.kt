package io.github.projektalmanac.imani.repositories

import io.github.projektalmanac.imani.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Int> {
}
