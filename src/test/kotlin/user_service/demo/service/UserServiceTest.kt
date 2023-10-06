package user_service.demo.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import user_service.demo.dto.UserDto
import user_service.demo.model.Users
import user_service.demo.repository.UserRepository

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun getUser() {
        val user: Mono<Users> = Mono.just(Users(1, "username", "SMS", "email", "phone"))
        val expected: Mono<UserDto> = Mono.just(UserDto(1, "username", "SMS", "email", "phone"))
        `when`(userRepository.findById(1))
                .thenReturn(user)

        val actual = userService.getUser(1)

        val expectedUserDto = expected.block()
        val actualUserDto = actual.block()
        Assertions.assertEquals(expectedUserDto, actualUserDto)
    }

    @Test
    fun getUsers() {
        val users: Flux<Users> = Flux.just(Users(1, "username", "SMS", "email", "phone"),
                Users(2, "username", "EMAIL", "email", "phone"))
        val expected: Flux<UserDto> = Flux.just(UserDto(1, "username", "SMS", "email", "phone"),
                UserDto(2, "username", "EMAIL", "email", "phone"))
        `when`(userRepository.findByIds(listOf(1, 2)))
                .thenReturn(users)

        val actual = userService.getUsers(listOf(1, 2))

        val expectedList = expected.collectList().block()
        val actualList = actual.collectList().block()
        Assertions.assertEquals(expectedList, actualList)
    }
}