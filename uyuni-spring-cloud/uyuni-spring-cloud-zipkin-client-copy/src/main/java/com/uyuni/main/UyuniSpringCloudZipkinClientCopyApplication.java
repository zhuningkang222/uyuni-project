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
public class UyuniSpringCloudZipkinClientCopyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudZipkinClientCopyApplication.class, args);
	}
	
	private static final Logger log = LoggerFactory.getLogger(UyuniSpringCloudZipkinClientCopyApplication.class);
	
	@RequestMapping("/hi")
    public String home(){
		log.info("hi is being called");
        return "hi i'm miya!";
    }
	
	/**
	 * 8989 client copy
	 * @return
	 */
    @RequestMapping("/miya")
    public String miya(){
    	log.info("info is being called");
        return restTemplate.getForObject("http://localhost:8988/info2",String.class);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }
}
