<p align="center"><img src="https://github.com/sonata-nfv/tng-api-gtw/wiki/images/sonata-5gtango-logo-500px.png" /></p>

# Netflix Load Balancing demo


## Demo steps


1. Build both `eureka service` and `ribbon client` components:

    ```
    cd eureka-service
    
    ./gradlew clean build
    
    cd ../ribbon-client
    
    ./gradlew clean build
    ```

2. Instantiate `eureka service` and `ribbon client` containers:

    ```
    docker-compose up --build 
    ```
3. Run un-containerized ribbon-client with different spring profile to browse the registered instances of eureka-service. 

   ```
   cd ../ribbon-client
   ./gradlew bootRun -Pprofiles=dev & ./gradlew bootRun -Pprofiles=dev
   ``` 

4. Browse through the ribbon client all registered service instances
    ```
    docker exec -it netflix-eureka_eureka-client_1 ash curl -s http://localhost:8080/service-instances/eureka-client | jq .
    ```

5. todo: need to handle the multiple client instances through docker-compose & kubernetes 