package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
        org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
        org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
})
@RestController
public class SpringbootAwsSqsExampleApplication {

	Logger logger= LoggerFactory.getLogger(SpringbootAwsSqsExampleApplication.class);

	
	@Autowired
	private QueueMessagingTemplate qmt;
	
	@Value("${cloud.aws.endpoint.uri}")
	private String endPoint;
	
	@GetMapping("/")
	public String hello() {
		return "welcome SQS";
	}
	@GetMapping("/send/{message}")
	public void sendMsgQueue(@PathVariable String message) {
		qmt.send(endPoint, MessageBuilder.withPayload(message).build());
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSqsExampleApplication.class, args);
	}

}
