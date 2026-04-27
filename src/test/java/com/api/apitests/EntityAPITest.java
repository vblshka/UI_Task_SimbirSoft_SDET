package com.api.apitests;

import com.api.dto.*;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EntityAPITest extends BaseAPITest {

    @Test
    public void getEntityByIdTest() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        CreateEntityRequest entityData = CreateEntityRequest.builder()
                .title("getByID test_" + timestamp)
                .verified(true)
                .build();

        String createResponse = given(specRequest)
                .body(entityData)
                .when()
                .post("/create")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        EntityDataResponse entityById = given(specRequest)
                .when()
                .get("/get/" + createResponse)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(EntityDataResponse.class);

        Assertions.assertEquals(entityData.getTitle(), entityById.getTitle());

    }

    @Test
    public void getAllEntitiesByTitleTest() {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        CreateEntityRequest entityData = CreateEntityRequest.builder()
                .title("getByTitle test_" + timestamp)
                .verified(true)
                .build();

        String createResponse = given(specRequest)
                .body(entityData)
                .when()
                .post("/create")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        EntitiesListResponse entities = given(specRequest)
                .when()
                .queryParam("title", "getByTitle test_" + timestamp)
                .get("/getAll")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EntitiesListResponse.class);

        String idEntityByTitle = String.valueOf(entities.getEntity().get(0).getId());

        Assertions.assertEquals(createResponse, idEntityByTitle);
    }

    @Test
    public void createEntityTest() {
        AdditionDataRequest additionData = AdditionDataRequest.builder()
                .additionalInfo("created")
                .additionalNumber(201)
                .build();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        CreateEntityRequest entityData = CreateEntityRequest.builder()
                .addition(additionData)
                .importantNumbers(List.of(2, 0, 1))
                .title("created entity test_" + timestamp)
                .verified(true)
                .build();

        String createResponse = given(specRequest)
                .body(entityData)
                .when()
                .post("/create")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        EntitiesListResponse getCreatedEntity = given(specRequest)
                .when()
                .queryParam("title", "created entity test_" + timestamp)
                .get("/getAll")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EntitiesListResponse.class);

        EntityDataResponse createdEntity = getCreatedEntity.getEntity().get(0);

        String expectedResult = String.valueOf(createdEntity.getId());

        Assertions.assertEquals(expectedResult, createResponse);
    }

    @Test
    public void deleteEntityTest() {
        CreateEntityRequest entityData = CreateEntityRequest.builder()
                .title("for delete entity test")
                .verified(true)
                .build();

        String createResponse = given(specRequest)
                .body(entityData)
                .when()
                .post("/create")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        given(specRequest)
                .when()
                .delete("/delete/" + createResponse)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        GetBodyAfterDelete entityById = given(specRequest)
                .when()
                .get("/get/" + createResponse)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .extract().body().as(GetBodyAfterDelete.class);

        Assertions.assertEquals("no rows in result set", entityById.getError());
    }

    @Test
    public void patchTitleTest() {
        AdditionDataRequest additionData = AdditionDataRequest.builder()
                .additionalInfo("created")
                .additionalNumber(201)
                .build();

        CreateEntityRequest entityData = CreateEntityRequest.builder()
                .addition(additionData)
                .title("patchTitle test_")
                .verified(true)
                .build();

        String createResponse = given(specRequest)
                .body(entityData)
                .when()
                .post("/create")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        AdditionDataRequest patchAdditionData = AdditionDataRequest.builder()
                .additionalInfo("patched")
                .additionalNumber(123)
                .build();

        CreateEntityRequest patchData = CreateEntityRequest.builder()
                .addition(patchAdditionData)
                .importantNumbers(List.of(1, 2, 3))
                .title("patch test")
                .verified(false)
                .build();

        given(specRequest)
                .body(patchData)
                .when()
                .patch("/patch/" + createResponse)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        EntityDataResponse entityById = given(specRequest)
                .when()
                .get("/get/" + createResponse)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(EntityDataResponse.class);

        Assertions.assertEquals(patchData.getTitle(), entityById.getTitle());
        Assertions.assertEquals(patchData.isVerified(), entityById.isVerified());
        Assertions.assertEquals(patchData.getAddition().getAdditionalInfo()
                , entityById.getAddition().getAdditionalInfo());
        Assertions.assertEquals(patchData.getAddition().getAdditionalNumber()
                , entityById.getAddition().getAdditionalNumber());
        Assertions.assertEquals(patchData.getImportantNumbers(), entityById.getImportantNumbers());
    }
}

