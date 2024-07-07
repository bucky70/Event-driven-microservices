package com.microservices.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Getter @Setter
@Configuration //to make this class a springbean
@ConfigurationProperties(prefix="twitter-to-kafka-service")
public class TwitterToKafkaServiceConfigData {
    private List<String> twitterKeywords;
    private String welcomeMessage;
    private boolean enableMockTweets;
    private Long mockSleepMs;
    private Integer mockMinTweetLength;
    private Integer mockMaxTweetLength;

    public List<String> getTwitterKeywords() {
        return twitterKeywords;
    }

    public void setTwitterKeywords(List<String> twitterKeywords) {
        this.twitterKeywords = twitterKeywords;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public boolean isEnableMockTweets() {
        return enableMockTweets;
    }

    public void setEnableMockTweets(boolean enableMockTweets) {
        this.enableMockTweets = enableMockTweets;
    }

    public Long getMockSleepMs() {
        return mockSleepMs;
    }

    public void setMockSleepMs(Long mockSleepMs) {
        this.mockSleepMs = mockSleepMs;
    }

    public Integer getMockMinTweetLength() {
        return mockMinTweetLength;
    }

    public void setMockMinTweetLength(Integer mockMinTweetLength) {
        this.mockMinTweetLength = mockMinTweetLength;
    }

    public Integer getMockMaxTweetLength() {
        return mockMaxTweetLength;
    }

    public void setMockMaxTweetLength(Integer mockMaxTweetLength) {
        this.mockMaxTweetLength = mockMaxTweetLength;
    }
}
