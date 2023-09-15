package com.example.profile.module.member

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {

    fun getMembers(): List<Member> = memberRepository.findAll()

    fun getMember(id: Int): Member? = memberRepository.findById(id).orElse(null)
}
