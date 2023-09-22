package com.example.profile.runner

import com.example.profile.config.AppConfig
import com.example.profile.config.SiteConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class AppRunner(
    @Value("\${me.profile}")
    private val profile: String,
    private val appConfig: AppConfig,
    private val siteConfig: SiteConfig
) : ApplicationRunner {

    private val logger = LoggerFactory.getLogger(AppRunner::class.java)

    override fun run(args: ApplicationArguments?) {
        logger.debug("AppRunner run")
        logger.debug("profile: $profile")
        logger.debug("appConfig.profile: ${appConfig.profile}")
        logger.debug("siteConfig.link: ${siteConfig.link}")
    }
}
