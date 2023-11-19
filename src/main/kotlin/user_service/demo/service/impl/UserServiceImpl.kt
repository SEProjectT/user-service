package user_service.demo.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import user_service.demo.dto.UserDto
import user_service.demo.mapper.UserMapper
import user_service.demo.repository.UserRepository
import user_service.demo.service.UserService
import java.util.logging.Logger

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {

    private var logger: Logger = Logger.getLogger(this::class.java.name)

    override fun getUser(id: Long): Mono<UserDto> =
        userRepository.findById(id)
            .map { UserMapper.toDto(it) }
            .doOnSuccess {
                logger.info("User with id: $id has been received")
            }


    override fun getUsers(ids: List<Long>): Flux<UserDto> =
        userRepository.findByIds(ids)
            .map { UserMapper.toDto(it) }
            .doOnComplete {
                logger.info("Users with ids: $ids have been received")
            }
}