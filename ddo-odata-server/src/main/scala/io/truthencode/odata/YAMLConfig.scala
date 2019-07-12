package io.truthencode.odata

import java.util

import org.springframework.boot.context.properties.{ConfigurationProperties, EnableConfigurationProperties}
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties class YAMLConfig {
  private val name = null
  private val environment = null
  private val servers = new util.ArrayList[String]
  // standard getters and setters
}
