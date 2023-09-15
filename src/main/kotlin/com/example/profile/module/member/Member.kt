package com.example.profile.module.member

import jakarta.persistence.*

@Entity
data class Member(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Id val id: Int?,
    val name: String
) {
}
