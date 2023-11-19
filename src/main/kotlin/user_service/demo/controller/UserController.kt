package user_service.demo.controller

import io.swagger.v3.oas.annotations.Operation
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
class UserController(val userService: UserService) {

    private var logger: Logger = Logger.getLogger(this::class.java.name)

    @GetMapping("/{id}")
    @Operation(summary = "Get user", description = "Get user by id")
    fun getUser(@PathVariable id: Long): Mono<UserDto> {
        logger.info("Getting user with id: $id")

        return userService.getUser(id)
    }

    @GetMapping("/")
    @Operation(summary = "Get users", description = "Get users by ids")
    fun getUsers(@RequestParam ids: List<Long>): Flux<UserDto> {
        logger.info("Getting users with ids: $ids")

        return userService.getUsers(ids)
    }
}