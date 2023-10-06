package user_service.demo.repository

import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Mono
import user_service.demo.model.Users
import org.springframework.data.r2dbc.core.*
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class UserRepository {

    @Autowired
    private lateinit var template: R2dbcEntityTemplate

    fun findById(id: Long): Mono<Users> {
        return template.selectOne(query(where("id").`is`(id)),
            Users::class.java)
    }

    fun findByIds(ids: List<Long>): Flux<Users> {
        return template.select(query(where("id").`in`(ids)),
            Users::class.java)
    }
}