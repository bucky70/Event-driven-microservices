package com.microservices.demo.twitter.to.kafka.service.runner;

import twitter4j.TwitterException;

public interface StreamRunner {
    default void start() throws TwitterException {

    }
}
