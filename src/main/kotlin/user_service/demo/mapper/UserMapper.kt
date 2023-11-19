package user_service.demo.mapper

import user_service.demo.dto.UserDto
import user_service.demo.model.PreferredContact
import user_service.demo.model.User

object UserMapper {

    fun toDto(user: User): UserDto =
        UserDto(
            id = user.id,
            username = user.username,
            preferredContact = PreferredContact.valueOf(user.preferredContact),
            email = user.email,
            phone = user.phone
        )
}