package com.uyuni.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class UyuniSpringCloudZipkinClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudZipkinClientApplication.class, args);
	}
	
	private static final Logger log = LoggerFactory.getLogger(UyuniSpringCloudZipkinClientApplication.class);
	
	@Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome(){
        log.info("calling trace spring cloud zipkin client  ");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }
    
    /**
     * 8988 client
     * @return
     */
    @RequestMapping("/info2")
    public String info2(){
    	log.info("calling trace spring cloud zipkin client ");
        return "i'm service-hi";

    }

    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }
}
