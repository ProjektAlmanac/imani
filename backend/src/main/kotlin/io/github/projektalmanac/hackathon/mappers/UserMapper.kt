package io.github.projektalmanac.hackathon.mappers

import io.github.projektalmanac.hackathon.entities.User
import io.github.projektalmanac.hackathon.generated.dto.PostUserRequestDto
import io.github.projektalmanac.hackathon.generated.dto.UserDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class UserMapper {
    abstract fun toUser(userDto: PostUserRequestDto): User

    abstract fun toUserDto(user: User): UserDto
}
