package user_service.demo.dto

data class UserDto(
    val id: Long,

    val username: String,

    val preferredContact: PreferredContact,

    val email: String,

    val phone: String
)