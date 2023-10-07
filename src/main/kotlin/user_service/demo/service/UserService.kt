package user_service.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import user_service.demo.dto.UserDto
import user_service.demo.repository.UserRepository

@Service
@Transactional(readOnly = true)
class UserService(@Autowired val userRepository: UserRepository) {

    fun getUser(id: Long): Mono<UserDto> {
        return userRepository.findById(id)
            .map { UserDto(it.id, it.username, it.preferredContact, it.email, it.phone) }
            .log()
    }

    fun getUsers(ids: List<Long>): Flux<UserDto> {
        return userRepository.findByIds(ids)
            .map { UserDto(it.id, it.username, it.preferredContact, it.email, it.phone) }
            .log()
    }
}