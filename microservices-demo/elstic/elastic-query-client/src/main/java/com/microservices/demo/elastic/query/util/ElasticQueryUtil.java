package com.microservices.demo.elastic.query.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static java.awt.SystemColor.text;

@Component
public class ElasticQueryUtil <T extends IndexModel>{
    public Query getSearchQueryById(String id){
        Query query = NativeQuery.builder()
                .withIds(Collections.singleton(id))
                .build();
        return query;
    }

    public Query getSearchQueryByFieldText(String field,String text){
        Query query = NativeQuery.builder()
                .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchQuery(field, text)))
                .build();
        return query;
    }

    public Query getSearchQueryForAll() {
        Query query = NativeQuery.builder()
                .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchAllQuery()))
                .build();
        return query;
    }

}
