package com.example.profile.module.types

import com.example.profile.module.types.dto.TypeResponseDto
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import java.net.URI

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //  MockMvc를 사용하기 위해 필요한 어노테이션
class TypeControllerWebTestClientTest(
) : DescribeSpec() {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @MockkBean
    lateinit var typesService: TypesService

    init {
        extension(SpringExtension)


        describe("test") {
            context("test") {
                it("111111111111111") {

                    val list = listOf(
                        TypeResponseDto(1, "system"),
                        TypeResponseDto(2, "samsung"),
                        TypeResponseDto(3, "hot")
                    )

                    every {
                        typesService.getTypes()
                    } returns list

                    val requestEntity = RequestEntity<Any>(HttpMethod.GET, URI.create("/types"))
                    val responseType = object : ParameterizedTypeReference<List<TypeResponseDto>>() {}
                    val responseEntity = testRestTemplate.exchange(requestEntity, responseType)
                    responseEntity.statusCode.is2xxSuccessful shouldBe true
                    responseEntity.body shouldBe list
                }
            }
        }
    }
}

