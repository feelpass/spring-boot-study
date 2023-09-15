package com.example.profile.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "me")
@EnableConfigurationProperties(SiteConfig::class)
class AppConfig {
    lateinit var profile: String
}
