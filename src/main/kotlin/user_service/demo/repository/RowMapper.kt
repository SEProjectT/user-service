package user_service.demo.repository

import io.r2dbc.spi.Row
import user_service.demo.model.User

object UserRowMapper {

    fun toEntity(row: Row, rowMetadata: Any): User =
        User(
            id = row.get("id") as Long,
            username = row.get("username") as String,
            preferredContact = row.get("preferred_contact") as String,
            email = row.get("email") as String,
            phone = row.get("phone") as String
        )
}