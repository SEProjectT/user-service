package user_service.demo.controller

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import user_service.demo.UserServiceApplication

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [UserServiceApplication::class])
@Testcontainers
class UserControllerIT {

    companion object {
        @Container
        var postgres: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13.3")
                .withInitScript("db/changelog/changeset/testcontainers/user_test_V001_initial.sql")

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            postgres.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url", Companion::r2dbcUrl)
            registry.add("spring.r2dbc.username", postgres::getUsername)
            registry.add("spring.r2dbc.password", postgres::getPassword)
            registry.add("spring.liquibase.url", postgres::getJdbcUrl)
            registry.add("spring.liquibase.user", postgres::getUsername)
            registry.add("spring.liquibase.password", postgres::getPassword)
        }

        fun r2dbcUrl(): String {
            return "r2dbc:postgresql://${postgres.host}:${postgres.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT)}/${postgres.databaseName}"
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            postgres.stop()
        }
    }

    @Autowired
    private lateinit var webTestClient: WebTestClient

    fun getUser() {
        webTestClient.get().uri("/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.username").isEqualTo("user1")
                .jsonPath("$.preferredContact").isEqualTo("SMS")
                .jsonPath("$.email").isEqualTo("email1")
                .jsonPath("$.phone").isEqualTo("phone1")
    }

    @Test
    fun getUsers() {
        webTestClient.get().uri("/users/?ids=1,2")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].username").isEqualTo("user1")
                .jsonPath("$[0].preferredContact").isEqualTo("SMS")
                .jsonPath("$[0].email").isEqualTo("email1")
                .jsonPath("$[0].phone").isEqualTo("phone1")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].username").isEqualTo("user2")
                .jsonPath("$[1].preferredContact").isEqualTo("EMAIL")
                .jsonPath("$[1].email").isEqualTo("email2")
                .jsonPath("$[1].phone").isEqualTo("phone2")
    }
}