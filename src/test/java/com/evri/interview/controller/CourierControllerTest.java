package com.evri.interview.controller;

import com.evri.interview.exceptions.CourierIsNotFound;
import com.evri.interview.model.Courier;
import com.evri.interview.model.CourierUpdate;
import com.evri.interview.service.CourierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourierController.class)
class CourierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierService courierService;

    @Test
    void testGetAllCouriers_Success() throws Exception {
        Courier courier1 = Courier.builder().id(1L).name("Courier Name1").active(true).build();
        Courier courier2 = Courier.builder().id(2L).name("Courier Name2").active(false).build();
        Courier courier3 = Courier.builder().id(3L).name("Courier Name3").active(true).build();
        List<Courier> couriers = Arrays.asList(courier1, courier2, courier3);

        Mockito.when(courierService.getAllCouriers(Mockito.anyBoolean())).thenReturn(couriers);

        mockMvc.perform(get("/api/couriers"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[0].name").value("Courier Name1"))
               .andExpect(jsonPath("$[0].active").value(true))
               .andExpect(jsonPath("$[1].id").value(2))
               .andExpect(jsonPath("$[1].name").value("Courier Name2"))
               .andExpect(jsonPath("$[1].active").value(false))
               .andExpect(jsonPath("$[2].id").value(3))
               .andExpect(jsonPath("$[2].name").value("Courier Name3"))
               .andExpect(jsonPath("$[2].active").value(true));
    }

    @Test
    void testGetAllCouriers_ExceptionHandling_InvalidParameter() throws Exception {
        Courier courier1 = Courier.builder().id(1L).name("Courier Name1").active(true).build();
        Courier courier2 = Courier.builder().id(2L).name("Courier Name2").active(false).build();
        Courier courier3 = Courier.builder().id(3L).name("Courier Name3").active(true).build();
        List<Courier> couriers = Arrays.asList(courier1, courier2, courier3);

        Mockito.when(courierService.getAllCouriers(Mockito.anyBoolean())).thenReturn(couriers);

        mockMvc.perform(get("/api/couriers?isActive=NOT_VALID"))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$.path").value("/api/couriers"))
               .andExpect(jsonPath("$.error").value("MethodArgumentTypeMismatchException"))
               .andExpect(jsonPath("$.status").value("500"));
    }

    @Test
    void testUpdateCourier_Success() throws Exception {
        CourierUpdate courierUpdate = CourierUpdate.builder().firstName("name").lastName("last").active(true).build();
        Courier courier1 = Courier.builder().id(1L).name("Courier Name1").active(true).build();

        Mockito.when(courierService.updateCourier(1L, courierUpdate))
               .thenReturn(courier1);

        mockMvc.perform(put("/api/couriers/{courierId}", 1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper().writeValueAsString(courierUpdate)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Courier Name1"))
               .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void testUpdateCourier_ExceptionHandling_CourierNotFound() throws Exception {
        CourierUpdate courierUpdate = CourierUpdate.builder().firstName("name").lastName("last").active(true).build();

        Mockito.when(courierService.updateCourier(Mockito.anyLong(), Mockito.any(CourierUpdate.class)))
               .thenThrow(new CourierIsNotFound("Not Found"));

        mockMvc.perform(put("/api/couriers/{courierId}", 1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper().writeValueAsString(courierUpdate)))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.path").value("/api/couriers/1"))
               .andExpect(jsonPath("$.error").value("CourierIsNotFound"))
               .andExpect(jsonPath("$.status").value("404"));

    }

    @Test
    void testUpdateCourier_ExceptionHandling_Validation() throws Exception {
        CourierUpdate courierUpdate = CourierUpdate.builder().firstName(null).lastName("").active(false).build();

        Mockito.when(courierService.updateCourier(Mockito.anyLong(), Mockito.any(CourierUpdate.class)))
               .thenThrow(new CourierIsNotFound("Not Found"));

        mockMvc.perform(put("/api/couriers/{courierId}", 1)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(new ObjectMapper().writeValueAsString(courierUpdate)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.path").value("/api/couriers/1"))
               .andExpect(jsonPath("$.error").value("MethodArgumentNotValidException"))
               .andExpect(jsonPath("$.status").value("400"));
    }

}