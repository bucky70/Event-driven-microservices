package com.microservices.demo.elastic.query.service.business;

import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceResponseModel;

public interface ElasticQueryService {

    ElasticQueryServiceResponseModel getDocumentById();

    ElasticQueryServiceResponseModel getDocumentById(String id);
}
