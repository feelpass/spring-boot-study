package com.example.profile.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "site")
class SiteConfig {
    lateinit var link: String
}
