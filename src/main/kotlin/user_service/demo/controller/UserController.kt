package user_service.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import user_service.demo.dto.UserDto
import user_service.demo.service.UserService
import java.util.logging.Logger

@RestController
@RequestMapping("/users")
class UserController(@Autowired val userService: UserService) {

    private var logger: Logger = Logger.getLogger(UserController::class.java.name)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): Mono<UserDto> {
        logger.info("Getting user with id: $id")

        return userService.getUser(id)
    }

    @GetMapping("/")
    fun getUsers(@RequestParam ids: List<Long>): Flux<UserDto> {
        logger.info("Getting users with ids: $ids")

        return userService.getUsers(ids)
    }
}