package com.evri.interview;

import com.evri.interview.model.CourierUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order is better to use only for integration tests that are changing data
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    void contextLoads() {
    }

    @Order(2)
    @Test
    void testGetAllCouriers_SuccessfulRetrieval() throws Exception {
        mockMvc.perform(get("/api/couriers"))
               .andExpect(status().isOk())

               .andExpect(jsonPath("$").isArray())

               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[0].name").value("Ben Askew"))
               .andExpect(jsonPath("$[0].active").value(true))

               .andExpect(jsonPath("$[1].id").value(2))
               .andExpect(jsonPath("$[1].name").value("Alex Lion"))
               .andExpect(jsonPath("$[1].active").value(true))

               .andExpect(jsonPath("$[2].id").value(3))
               .andExpect(jsonPath("$[2].name").value("Gloria Hippopotamus"))
               .andExpect(jsonPath("$[2].active").value(false))

               .andExpect(jsonPath("$[3].id").value(4))
               .andExpect(jsonPath("$[3].name").value("Marty Zebra"))
               .andExpect(jsonPath("$[3].active").value(false))

               .andExpect(jsonPath("$[4].id").value(5))
               .andExpect(jsonPath("$[4].name").value("Melman Giraffe"))
               .andExpect(jsonPath("$[4].active").value(true));
    }

    @Order(3)
    @Test
    void testUpdateCourier_SuccessfulUpdate() throws Exception {
        CourierUpdate courierUpdate = CourierUpdate.builder().firstName("name").lastName("last").active(false).build();

        mockMvc.perform(put("/api/couriers/{courierId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)
                                                           .content(new ObjectMapper().writeValueAsString(courierUpdate)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("name last"))
               .andExpect(jsonPath("$.active").value(false));
    }

}
