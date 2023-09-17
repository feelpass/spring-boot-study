package com.example.profile

import com.example.profile.config.AppConfig
import com.example.profile.config.SiteConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("local")
@SpringBootTest(classes = [OneSwipeApplication::class])
class AppApplicationTests : DescribeSpec() {

    @Autowired
    lateinit var appConfig: AppConfig

    @Autowired
    lateinit var siteConfig: SiteConfig

    init {
        extension(SpringExtension)
        this.describe("AppConfig") {
            context("appConfig.nickname") {
                it("should be feelpass222") {
                    appConfig.profile shouldBe "local"
                    siteConfig.link shouldBe "https://feelpass.cn"
                }
            }
        }

    }
}

