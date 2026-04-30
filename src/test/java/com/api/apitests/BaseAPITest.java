package com.api.apitests;

import com.api.config.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;

public abstract class BaseAPITest {
    protected static final Configuration config = ConfigFactory.create(Configuration.class, System.getenv());
    protected static final RequestSpecification specRequest = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(config.baseUrl())
            .setBasePath(config.basePath())
            .log(LogDetail.ALL)
            .build();
    protected static final ResponseSpecification specResponse = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL)
            .build();
}
