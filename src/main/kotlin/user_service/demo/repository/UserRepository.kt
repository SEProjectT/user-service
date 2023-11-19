package user_service.demo.repository

import reactor.core.publisher.Mono
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import user_service.demo.model.User

interface UserRepository {
    fun findById(id: Long): Mono<User>

    fun findByIds(ids: List<Long>): Flux<User>
}

@Repository
class UserRepositoryImpl(
    private val client: DatabaseClient
): UserRepository {

    companion object {
        const val FIND_BY_ID = """
            select * from users
            where id = :userId
        """

        const val FIND_BY_IDs = """
            select * from users
            where id in (:userIds)
        """
    }

    override fun findById(id: Long): Mono<User> =
        client
            .sql(FIND_BY_ID)
            .bind("userId", id)
            .map(UserRowMapper::toEntity)
            .one()

    override fun findByIds(ids: List<Long>): Flux<User> =
        client
            .sql(FIND_BY_IDs)
            .bind("userIds", ids)
            .map(UserRowMapper::toEntity)
            .all()
}