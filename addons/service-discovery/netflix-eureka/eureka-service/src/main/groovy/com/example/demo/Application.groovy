package com.example.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.cloud.netflix.ribbon.RibbonClient


@SpringBootApplication
@EnableEurekaServer
class Application {

	static void main(String[] args) {
		SpringApplication.run(Application, args)
	}

}

