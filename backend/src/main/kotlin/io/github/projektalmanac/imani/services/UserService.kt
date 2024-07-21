package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.generated.dto.PostUserRequestDto
import io.github.projektalmanac.imani.generated.dto.UserDto
import io.github.projektalmanac.imani.mappers.UserMapper
import io.github.projektalmanac.imani.repositories.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService(val userRepository: UserRepository, val userMapper: UserMapper) {

    fun addUser(postUserRequestDto: PostUserRequestDto?): UserDto {
        requireNotNull(postUserRequestDto)

        var user = userMapper.toUser(postUserRequestDto)
        user.createDate = LocalDate.now()

        user = userRepository.save(user);

        return userMapper.toUserDto(user)
    }
}
