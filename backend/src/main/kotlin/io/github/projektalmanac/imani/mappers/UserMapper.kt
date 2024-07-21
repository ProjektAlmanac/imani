package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.User
import io.github.projektalmanac.imani.generated.dto.PostUserRequestDto
import io.github.projektalmanac.imani.generated.dto.UserDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class UserMapper {
    abstract fun toUser(userDto: PostUserRequestDto): User

    abstract fun toUserDto(user: User): UserDto
}
