package com.microservices.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private String topicName;
    private List<String> topicNameToCreate;
    private int numOfPartitions;
    private int replicationFactor;

    // Getters and Setters

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getSchemaRegistryUrlKey() {
        return schemaRegistryUrlKey;
    }

    public void setSchemaRegistryUrlKey(String schemaRegistryUrlKey) {
        this.schemaRegistryUrlKey = schemaRegistryUrlKey;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public void setSchemaRegistryUrl(String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getTopicNameToCreate() {
        return topicNameToCreate;
    }

    public void setTopicNameToCreate(List<String> topicNameToCreate) {
        this.topicNameToCreate = topicNameToCreate;
    }

    public int getNumOfPartitions() {
        return numOfPartitions;
    }

    public void setNumOfPartitions(int numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public int getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(int replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    @Override
    public String toString() {
        return "KafkaConfig{" +
                "bootstrapServers='" + bootstrapServers + '\'' +
                ", schemaRegistryUrlKey='" + schemaRegistryUrlKey + '\'' +
                ", schemaRegistryUrl='" + schemaRegistryUrl + '\'' +
                ", topicName='" + topicName + '\'' +
                ", topicNameToCreate=" + topicNameToCreate +
                ", numOfPartitions=" + numOfPartitions +
                ", replicationFactor=" + replicationFactor +
                '}';
    }
}

