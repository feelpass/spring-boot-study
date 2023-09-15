package com.example.profile.module.member

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(val memberService: MemberService) {

    @GetMapping("/member")
    fun getMembers(): ResponseEntity<List<Member>> {
        val list = memberService.getMembers()
        return ResponseEntity.ok(list)
    }

    @GetMapping("/member/{id}")
    fun getMember(@PathVariable id: Int): ResponseEntity<Member> {
        val member = memberService.getMember(id)
        return ResponseEntity.ok(member)
    }
}
