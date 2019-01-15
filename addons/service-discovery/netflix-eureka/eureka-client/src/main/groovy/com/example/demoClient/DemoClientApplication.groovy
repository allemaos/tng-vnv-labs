package com.example.demoClient

import com.netflix.discovery.EurekaClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoClientApplication {

	static void main(String[] args) {
		SpringApplication.run(DemoClientApplication, args)
	}
}

@RestController
class ServiceInstanceRestController {

    @Value('${eureka.instance.instance-id}')
    def instanceId

    @Autowired
    private DiscoveryClient discoveryClient

    @RequestMapping("/")
    public String home() {
        return "Hello world from $instanceId";
    }

        @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        this.discoveryClient.getInstances(applicationName)
    }
}

