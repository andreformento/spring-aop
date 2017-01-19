package com.formento.aop.api.v1.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateProductWhenIsValid() {
        // given
        final String jsonProduct = "{\"name\" : \"andre\", \"price\": 15 }";
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> httpEntity = new HttpEntity<>(jsonProduct, headers);

        // when
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("/api/v1/products", httpEntity, String.class);

        // then
        assertEquals(responseEntity.toString(), HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity);
        assertTrue(responseEntity.hasBody());
        JSONAssert.assertEquals(jsonProduct, responseEntity.getBody(), false);
    }

    @Test
    public void shouldNotCreateProductWhenValueIsLessThanMin() {
        // given
        final String jsonProduct = "{\"name\" : \"andre\", \"price\": 3 }";
        final String expectedMessage = "{\"message\": \"Price cannot be less than 10\"}";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> httpEntity = new HttpEntity<>(jsonProduct, headers);

        // when
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("/api/v1/products", httpEntity, String.class);

        // then
        assertEquals(responseEntity.toString(), HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity);
        assertTrue(responseEntity.hasBody());
        JSONAssert.assertEquals(expectedMessage, responseEntity.getBody(), false);

    }

    @Test
    public void shouldNotCreateProductWhenValueIsMoreThanMax() {
        // given
        final String jsonProduct = "{\"name\" : \"andre\", \"price\": 66000 }";
        final String expectedMessage = "{\"message\": \"Price cannot be more than 10000\"}";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> httpEntity = new HttpEntity<>(jsonProduct, headers);

        // when
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("/api/v1/products", httpEntity, String.class);

        // then
        assertEquals(responseEntity.toString(), HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity);
        assertTrue(responseEntity.hasBody());
        JSONAssert.assertEquals(expectedMessage, responseEntity.getBody(), false);
    }

}
