package user_service.demo.model

import user_service.demo.dto.PreferredContact

data class Users(
    val id: Long,

    val username: String,

    val preferredContact: PreferredContact,

    val email: String,

    val phone: String
)