package io.github.projektalmanac.hackathon.repositories

import io.github.projektalmanac.hackathon.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Int> {
}
