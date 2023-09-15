package com.example.profile.runner

import com.example.profile.config.AppConfig
import com.example.profile.config.SiteConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class AppRunner : ApplicationRunner {
    @Value("\${me.profile}")
    lateinit var profile: String

    @Autowired
    lateinit var appConfig: AppConfig

    @Autowired
    lateinit var siteConfig: SiteConfig

    override fun run(args: ApplicationArguments?) {
        println("profile: $profile")
        println("appConfig.profile: ${appConfig.profile}")
        println("siteConfig.link: ${siteConfig.link}")
    }
}
