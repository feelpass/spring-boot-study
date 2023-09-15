package com.example.profile.module.member

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
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("local")
@ExtendWith(MockKExtension::class)
@SpringBootTest()
class MemberServiceTest : DescribeSpec() {


    @Autowired
    private lateinit var memberService: MemberService

    @MockkBean
    private lateinit var memberRepository: MemberRepository

    init {
        extension(SpringExtension)

        this.describe("test for repository") {

            context("members") {
                every { memberRepository.findAll() } returns listOf(
                    Member(1, "userA"),
                    Member(2, "userB"),
                    Member(3, "userC")
                )
                it("getMembers") {
                    val members = memberService.getMembers()
                    members shouldBe listOf(Member(1, "userA"), Member(2, "userB"), Member(3, "userC"))
                    verify {
                        memberRepository.findAll()
                    }
                    confirmVerified(memberRepository)
                }
            }


            context("members2") {
                every { memberRepository.findByIdOrNull(any()) } returns Member(1, "userA")
                it("getMember") {
                    val member = memberService.getMember(1)
                    member shouldBe Member(1, "userA")
                    verify {
                        memberRepository.findByIdOrNull(any())
                    }
                    confirmVerified(memberRepository)

                }
            }
        }
    }


}
