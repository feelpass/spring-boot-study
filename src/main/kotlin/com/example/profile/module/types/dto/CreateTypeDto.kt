package com.example.profile.module.types.dto

import com.example.profile.module.types.entity.Type

data class CreateTypeDto(
    val name: String
)

fun CreateTypeDto.toEntity(): Type {
    return Type(
        id = null,
        name = this.name
    )
}
