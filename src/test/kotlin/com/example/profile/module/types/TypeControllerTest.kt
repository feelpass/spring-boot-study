package com.example.profile.module.types

import com.example.profile.module.types.dto.CreateTypeDto
import com.example.profile.module.types.dto.TypeResponseDto
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc //  MockMvc를 사용하기 위해 필요한 어노테이션
class TypeControllerTest(

    val context: WebApplicationContext,
    val typesController: TypeController,
    val objectMapper: ObjectMapper
) : DescribeSpec() {

    lateinit var mockMvc: MockMvc

    init {
        extension(SpringExtension)

        // beforeeach
        beforeEach {
            mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true)) // 필터 추가
                .alwaysDo<DefaultMockMvcBuilder>(::print) // 모든 요청과 응답을 콘솔에 출력
                .build()
        }

        afterEach {

        }

        describe("test") {
            context("test") {
                it("test") {
                    val list = listOf(
                        TypeResponseDto(1, "system"),
                        TypeResponseDto(2, "samsung"),
                        TypeResponseDto(3, "hot")
                    )
                    val result = typesController.getTypes()
                    result.body shouldBe list
                }
                it("test2") {
                    val result = mockMvc.perform(get("/types/1").accept(MediaType.APPLICATION_JSON))
                    result.andExpect(status().isOk)
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.name").value("system"))
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
            }
        }
    }
}

