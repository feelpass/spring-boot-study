package com.example.profile.module.types

import com.example.profile.module.types.dto.CreateTypeDto
import com.example.profile.module.types.dto.TypeResponseDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc //  MockMvc를 사용하기 위해 필요한 어노테이션
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TypeControllerTest(
    val context: WebApplicationContext,
    val typesController: TypeController,
    val objectMapper: ObjectMapper,
    val mockMvc: MockMvc,
) : DescribeSpec() {

    @MockkBean(relaxed = true)
    lateinit var typesService: TypesService

    init {
        extension(SpringExtension)


        describe("test") {

            context("test") {
                it("get all") {
                    every {
                        typesService.getTypes()
                    } answers {
                        listOf(
                            TypeResponseDto(1, "system"),
                            TypeResponseDto(2, "samsung"),
                            TypeResponseDto(3, "hot")
                        )
                    }

                    val list = listOf(
                        TypeResponseDto(1, "system"),
                        TypeResponseDto(2, "samsung"),
                        TypeResponseDto(3, "hot")
                    )
                    val result = typesController.getTypes()
                    result.body shouldBe list
                }


                it("get one") {
                    every {
                        typesService.getType(any())
                    } answers {
                        TypeResponseDto(1, "system")
                    }
                    val result = mockMvc.perform(get("/types/1").accept(MediaType.APPLICATION_JSON))
                    result.andExpect(status().isOk)
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.type_name").value("system"))
                }

                it("get two") {
                    every {
                        typesService.getType(any())
                    } answers {
                        TypeResponseDto(1, "system")
                    }
                    val result = mockMvc.perform(get("/types/1").accept(MediaType.APPLICATION_JSON))
                    result.andExpect(status().isOk)
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.type_name").value("system"))
                }

                it("create") {
                    val result = mockMvc.perform(
                        post("/types")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(CreateTypeDto("test")))
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    result.andExpect(status().isOk)
                }

                it("delete") {
                    val result = mockMvc.perform(
                        delete("/types/1")
                    )
                    result.andExpect(status().isOk)
                }
            }
        }
    }
}

