package com.example.profile

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class OneSwipeApplication

fun main(args: Array<String>) {
    runApplication<OneSwipeApplication>(*args)
}
