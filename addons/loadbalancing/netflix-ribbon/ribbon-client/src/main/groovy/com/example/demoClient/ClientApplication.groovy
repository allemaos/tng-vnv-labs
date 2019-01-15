package com.example.demoClient

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableDiscoveryClient
class ClientApplication {

	static void main(String[] args) {
		SpringApplication.run(ClientApplication, args)
	}
}

@RestController
class ServiceInstanceRestController {

    private static Logger log = LoggerFactory.getLogger(ClientApplication.class);

    @Value('${eureka.instance.instance-id}')
    def instanceId

    @Autowired
    private DiscoveryClient discoveryClient

    @RequestMapping("/")
    String home() {
        log.info("#~#:Access /")
        return "Hi from $instanceId"
    }

    @RequestMapping("/service-instances/{applicationName}")
    List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        log.info("#~#:Access /service-instances/{applicationName}")
        this.discoveryClient.getInstances(applicationName)
    }

    @RequestMapping("/greetings")
    String greet() {
        log.info("#~#:Access /greeting")
        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations")
        def rand = new Random()
        greetings.get(rand.nextInt(greetings.size()))
    }
}

