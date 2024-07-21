package com.microservices.demo.elastic.query.service.model;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticQueryServiceRequestModel {
    private String id;
    @NonNull
    private String text;
}
