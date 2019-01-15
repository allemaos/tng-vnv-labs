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
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.http.ResponseEntity

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

@SpringBootApplication
@EnableDiscoveryClient
class UserApplication {

	static void main(String[] args) {
		SpringApplication.run(UserApplication, args)
	}
}


@RestController
class ServiceInstanceRestController {

    private static Logger log = LoggerFactory.getLogger(UserApplication.class)

    @Autowired
    private LoadBalancerClient loadBalancer


    @RequestMapping("/")
    public String root() {
        log.info("#~#:Access /")
        "Root User Response"
    }

    @RequestMapping("/hi")
    String hi( ) {
        log.info("#~#:Access /hi")
        getGreeting()
    }

    @RequestMapping("/greeting")
    String greet() {
        log.info("#~#:Access /greeting")
        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations")
        Random rand = new Random()
        int randomNum = rand.nextInt(greetings.size())
        greetings.get(randomNum)
    }

    String getGreeting() throws RestClientException, IOException {

        ServiceInstance serviceInstance=loadBalancer.choose("ribbon-client")
        String baseUrl=serviceInstance?.getUri().toString()
        if(baseUrl==null)
            return "#~#:Response: NO_CONNECTED_CLIENT_ERROR"

        RestTemplate restTemplate = new RestTemplate()
        String response = restTemplate.getForObject(baseUrl+"/greetings", String.class)
        log.info("#~#:Response: " + response)
        response;
    }

}


