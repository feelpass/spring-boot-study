package com.example.profile.module.types.dto

import com.example.profile.module.types.entity.Type

data class TypeResponseDto(
    val id: Int,
    val typeName: String
)

fun Type.toDto(): TypeResponseDto {
    if (this.id == null) throw IllegalArgumentException("id is null")
    return TypeResponseDto(
        id = this.id,
        typeName = this.name
    )
}
