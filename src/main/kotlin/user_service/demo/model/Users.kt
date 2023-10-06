package user_service.demo.model

data class Users(
    val id: Long,

    val username: String,

    val preferredContact: String,

    val email: String,

    val phone: String
)