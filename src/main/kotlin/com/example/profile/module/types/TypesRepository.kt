package com.example.profile.module.types

import com.example.profile.module.types.entity.Type
import org.springframework.data.jpa.repository.JpaRepository

interface TypesRepository : JpaRepository<Type, Int>
