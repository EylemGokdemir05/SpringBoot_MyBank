package org.eylem.mybank;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.eylem.mybank.entity.Card;
import org.eylem.mybank.entity.Customer;
import org.eylem.mybank.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MybankApplicationTests {

    @LocalServerPort
    private String portno;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testBoundaryEqual() {
        Card card=new Card();
        assertEquals(2000,card.getBoundary());
    }

    @Test
    @Order(1)
    void testCreateCustomer(){
        String url="http://localhost:" + portno+
                "/customer-controller/create";
        Customer customer= CustomerDto.builder()
                .name("Eylem")
                .surname("GOKDEMIR")
                .build().toCustomer();
        ResponseEntity<Customer> entity=restTemplate.postForEntity(url,customer,Customer.class);
        assertNotNull(entity.getBody());
    }
}