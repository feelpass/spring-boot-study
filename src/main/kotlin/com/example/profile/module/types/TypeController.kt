package com.example.profile.module.types

import com.example.profile.module.types.dto.CreateTypeDto
import com.example.profile.module.types.dto.TypeResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TypeController(val typeService: TypesService) {

    @GetMapping("/types")
    fun getTypes(): ResponseEntity<List<TypeResponseDto>> {
        val list = typeService.getTypes()
        return ResponseEntity.ok(list)
    }

    @GetMapping("/types/{id}")
    fun getType(@PathVariable id: Int): ResponseEntity<TypeResponseDto> {
        val member = typeService.getType(id)
        return ResponseEntity.ok(member)
    }

    @PostMapping("/types")
    fun createType(@RequestBody type: CreateTypeDto): ResponseEntity<TypeResponseDto> {
        val created = typeService.createType(type)
        return ResponseEntity.ok(created)
    }

    @DeleteMapping("/types/{id}")
    fun deleteType(@PathVariable id: Int): ResponseEntity<Unit> {
        typeService.deleteById(id)
        return ResponseEntity.ok().build()
    }
}
