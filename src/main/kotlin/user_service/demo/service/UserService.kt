package user_service.demo.service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import user_service.demo.dto.UserDto

interface UserService {

    fun getUser(id: Long): Mono<UserDto>

    fun getUsers(ids: List<Long>): Flux<UserDto>
}