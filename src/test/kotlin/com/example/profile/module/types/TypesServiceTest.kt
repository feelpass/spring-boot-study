package com.example.profile.module.types

import com.example.profile.module.types.dto.TypeResponseDto
import com.example.profile.module.types.entity.Type
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*


@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
@SpringBootTest()
class TypesServiceTest : DescribeSpec() {


    @Autowired
    private lateinit var typesService: TypesService

    @MockkBean
    private lateinit var typesRepository: TypesRepository

    init {
        extension(SpringExtension)

        this.describe("test for repository") {

            context("types") {
                every { typesRepository.findAll() } returns listOf(
                    Type(1, "userA"),
                    Type(2, "userB"),
                    Type(3, "userC")
                )
                it("getMembers") {
                    val types: List<TypeResponseDto> = typesService.getTypes()
                    types shouldBe listOf(TypeResponseDto(1, "userA"), TypeResponseDto(2, "userB"), TypeResponseDto(3, "userC"))
                    verify {
                        typesRepository.findAll()
                    }
                    confirmVerified(typesRepository)
                }
            }


            context("types2") {
                every { typesRepository.findById(any()) } returns Optional.of(Type(1, "userA"))
                it("getMember") {
                    val member: TypeResponseDto = typesService.getType(1)
                    member shouldBe TypeResponseDto(1, "userA")
                    verify {
                        typesRepository.findById(any())
                    }
                    confirmVerified(typesRepository)

                }
            }
        }
    }


}
