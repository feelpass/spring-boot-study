package com.example.profile.module.types

import com.example.profile.module.types.dto.CreateTypeDto
import com.example.profile.module.types.dto.TypeResponseDto
import com.example.profile.module.types.dto.toDto
import com.example.profile.module.types.dto.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TypesService(private val typesRepository: TypesRepository) {

    fun getTypes(): List<TypeResponseDto> = typesRepository.findAll().stream().map { it.toDto() }.toList()

    fun getType(id: Int): TypeResponseDto = typesRepository.findById(id).orElseThrow {
        IllegalArgumentException("not found: $id")
    }.toDto()

    @Transactional
    fun createType(createTypeDto: CreateTypeDto): TypeResponseDto {
        return typesRepository.save(createTypeDto.toEntity()).toDto()
    }

    fun deleteById(id: Int) {
        typesRepository.deleteById(id)
    }
}
