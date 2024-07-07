package com.microservices.demo.twitter.to.kafka.service.runner.impl;


import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.exception.TwitterToKafkaServiceException;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

@Component
@ConditionalOnProperty(name="twitter-to-kafka-service.enable-mock-tweets", havingValue = "false")
public class MockKafkaStreamRunner implements StreamRunner {
    private static final Logger LOG= LoggerFactory.getLogger(MockKafkaStreamRunner.class);

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    private final TwitterKafkaStatusListener twitterKafkaStatusListener;

    private static final Random RANDOM=new Random();
    private static final String[] WORDS= new String[]{
            "Lorem", "ipsum", "dolor", "sit", "amet,", "consectetur",
            "adipiscing", "elit,", "sed", "do", "eiusmod", "tempor",
            "incididunt", "ut", "labore", "et", "dolore", "magna",
            "aliqua.", "Ut", "enim", "ad", "minim", "veniam,", "quis",
            "nostrud", "exercitation", "ullamco", "laboris", "nisi",
            "ut", "aliquip", "ex", "ea", "commodo", "consequat.",
            "Duis", "aute", "irure", "dolor", "in", "reprehenderit",
            "in", "voluptate", "velit", "esse", "cillum", "dolore",
            "eu", "fugiat", "nulla", "pariatur.", "Excepteur", "sint",
            "occaecat", "cupidatat", "non", "proident,", "sunt", "in",
            "culpa", "qui", "officia", "deserunt", "mollit", "anim",
            "id", "est", "laborum."
    };
    private static final String tweetAsRawJson ="{"+
            "\"created_at\":\"{0}\"," +
            "\"id\":\"{1}\","+
            "\"text\":\"{2}\","+
            "\"user\":{\"id\":\"{3}\"}"+
            "}";
    private static final String TWITTER_STATUS_DATE_FORMAT="EEE MMM dd HH:mm:ss zzz yyyy";
    public MockKafkaStreamRunner(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData, TwitterKafkaStatusListener twitterKafkaStatusListener) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
        this.twitterKafkaStatusListener = twitterKafkaStatusListener;
    }

    @Override
    public void start() throws TwitterException {
        String[] keywords= twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        int minTweetLength= twitterToKafkaServiceConfigData.getMockMinTweetLength();
        int maxTweetLength= twitterToKafkaServiceConfigData.getMockMaxTweetLength();
        long sleepTimeMs= twitterToKafkaServiceConfigData.getMockSleepMs();
        LOG.info("Starting mock filtering twitter streams for keywords {}", Arrays.toString(keywords));
        simulateTwitterStream(keywords, minTweetLength, maxTweetLength, sleepTimeMs);
        // StreamRunner.super.start();
    }

    private void simulateTwitterStream(String[] keywords, int minTweetLength, int maxTweetLength, long sleepTimeMs)  {
        Executors.newSingleThreadExecutor().submit(()->{
            try{
                while(true) {
                    String formattedTweetAsRawJson = getFormattedTweet(keywords, minTweetLength, maxTweetLength);
                    Status status = TwitterObjectFactory.createStatus(formattedTweetAsRawJson);
                    twitterKafkaStatusListener.onStatus(status);
                    sleep(sleepTimeMs);
                }
            }catch(TwitterException e){
                    LOG.error("Error creating twitter status", e);
                }
        });

    }

    private void sleep(long sleepTimeMs) {
        try{
            Thread.sleep(sleepTimeMs);
        }catch (InterruptedException e){
            throw new TwitterToKafkaServiceException("Error while sleeping, waiting for new status to cretae !");
        }
    }
    private String getFormattedTweet(String[] keywords, int minTweetLength, int maxTweetLength) {
        String[] params=new String[]{
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern(TWITTER_STATUS_DATE_FORMAT)),
                String.valueOf(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE)),
                getRandomTweetContent(keywords,minTweetLength,maxTweetLength),
                String.valueOf(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE))
        };
        return formatTweetAsJsonWithParams(params);
    }

    private static String formatTweetAsJsonWithParams(String[] params) {
        String tweet=tweetAsRawJson;
        for(int i = 0; i< params.length; i++){
            tweet=tweet.replace("{"+i+"}", params[i]);
        }
        return tweet;
    }

    private String getRandomTweetContent(String[] keywords, int minTweetLength, int maxTweetLength) {
        StringBuilder tweet=new StringBuilder();
        int tweetLength= RANDOM.nextInt(maxTweetLength-minTweetLength+1) + minTweetLength;
        return constructRandomTweet(keywords, tweetLength, tweet);
    }

    private static String constructRandomTweet(String[] keywords, int tweetLength, StringBuilder tweet) {
        for (int i = 0; i < tweetLength; i++) {
            tweet.append((WORDS[RANDOM.nextInt(WORDS.length)])).append(" ");
            if(i== tweetLength /2){
                tweet.append(keywords[RANDOM.nextInt(keywords.length)]).append(" ");
            }
        }
        return tweet.toString().trim();
    }


}
