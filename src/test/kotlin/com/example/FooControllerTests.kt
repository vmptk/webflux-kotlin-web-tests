package com.example

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.*

@RunWith(SpringRunner::class)
@WebFluxTest
class FooControllerTests {

	@Autowired
	private lateinit var client: WebTestClient

	@MockBean
	private lateinit var repository: FooRepository

	@Test
	fun fooTest() {
		given(repository.foo()).willReturn("foo")
		client.get().uri("/controller/foo").exchange()
				.expectStatus().isOk
				// .expectBody<String>().isEqualTo("foo") with Spring Framework 5.O.6
				.expectBody<String>().returnResult().apply { assertEquals("foo", responseBody) }
	}
}