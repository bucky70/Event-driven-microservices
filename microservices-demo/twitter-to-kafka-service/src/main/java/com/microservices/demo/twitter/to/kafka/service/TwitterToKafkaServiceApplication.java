package com.microservices.demo.twitter.to.kafka.service;
import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;


@SpringBootApplication
//@Scope("request")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private static final Logger LOG= LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
   // @Autowired
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    private final StreamRunner streamRunner;
    //constructor injection allows bean to be immutable, using final keyword
    public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData, StreamRunner streamRunner) {
        this.twitterToKafkaServiceConfigData=twitterToKafkaServiceConfigData;
        this.streamRunner=streamRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("TwitterToKafkaServiceApplication started.....");
        LOG.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[]{})));
        LOG.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
        streamRunner.start();
    }
  //below are for application initialization logic
  /*  @PostConstruct  //You can apply the @PostConstruct annotation to a method within a Spring Bean to indicate that it should be executed after the bean has been constructed and its dependencies have been injected.
    public void init(){ //will be called once after the spring bean is created, spring beans are singleton so they are created once

    }*/
   /* @Override //implements ApplicationListener
    public void onApplicationEvent( ApplicationEvent event) {

    }*/

}
