package user_service.demo.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import user_service.demo.dto.UserDto
import user_service.demo.repository.UserRepository
import user_service.demo.service.UserService

@Service
@Transactional(readOnly = true)
class UserServiceImpl(@Autowired val userRepository: UserRepository) : UserService {

    override fun getUser(id: Long): Mono<UserDto> {
        return userRepository.findById(id)
            .map { UserDto(it.id, it.username, it.preferredContact, it.email, it.phone) }
            .log()
    }

    override fun getUsers(ids: List<Long>): Flux<UserDto> {
        return userRepository.findByIds(ids)
            .map { UserDto(it.id, it.username, it.preferredContact, it.email, it.phone) }
            .log()
    }
}