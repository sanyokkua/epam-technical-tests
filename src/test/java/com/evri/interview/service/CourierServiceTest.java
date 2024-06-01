package com.evri.interview.service;

import com.evri.interview.exceptions.CourierIsNotFound;
import com.evri.interview.model.Courier;
import com.evri.interview.model.CourierUpdate;
import com.evri.interview.repository.CourierEntity;
import com.evri.interview.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourierServiceTest {

    @Mock
    private CourierRepository courierRepositoryMock;
    @Spy
    private CourierTransformer courierTransformerSpy;

    @InjectMocks
    private CourierService courierService;

    @Test
    void testGetAllCouriers_ActiveOnly() {
        CourierEntity courier1 = CourierEntity.builder()
                                              .id(1)
                                              .active(true)
                                              .firstName("name1")
                                              .lastName("last1")
                                              .build();
        CourierEntity courier2 = CourierEntity.builder()
                                              .id(2)
                                              .active(false)
                                              .firstName("name2")
                                              .lastName("last2")
                                              .build();

        Mockito.when(courierRepositoryMock.findByActiveTrue()).
               thenReturn(Collections.singletonList(courier1));
        Mockito.when(courierRepositoryMock.findAll())
               .thenReturn(Arrays.asList(courier1, courier2));

        List<Courier> result1 = courierService.getAllCouriers(true);
        assertNotNull(result1);
        assertFalse(result1.isEmpty());
        assertEquals(1, result1.size());
        assertEquals(courier1.getId(), result1.get(0).getId());
        Mockito.verify(courierRepositoryMock, Mockito.times(1)).findByActiveTrue();
        Mockito.verify(courierRepositoryMock, Mockito.times(0)).findAll();
        Mockito.verify(courierTransformerSpy, Mockito.times(1)).toCourier(Mockito.any());

        List<Courier> result2 = courierService.getAllCouriers(false);
        assertNotNull(result2);
        assertFalse(result2.isEmpty());
        assertEquals(2, result2.size());
        assertEquals(courier1.getId(), result2.get(0).getId());
        assertEquals(courier2.getId(), result2.get(1).getId());
        Mockito.verify(courierRepositoryMock, Mockito.times(1)).findAll();
        Mockito.verify(courierRepositoryMock, Mockito.times(1)).findByActiveTrue(); // No additional calls
        Mockito.verify(courierTransformerSpy, Mockito.times(3)).toCourier(Mockito.any());
    }

    @Test
    void testUpdateCourierWhenFound_RaiseExceptionWhenCourierNotFound() {
        CourierEntity courier1 = CourierEntity.builder()
                                              .id(1)
                                              .active(true)
                                              .firstName("name1")
                                              .lastName("last1")
                                              .build();
        Mockito.when(courierRepositoryMock.findById(1L)).
               thenReturn(Optional.of(courier1));
        Mockito.when(courierRepositoryMock.findById(666L))
               .thenReturn(Optional.empty());
        Mockito.when(courierRepositoryMock.save(Mockito.any()))
               .thenReturn(courier1);

        CourierUpdate updateMock = Mockito.mock(CourierUpdate.class);
        Mockito.when(updateMock.getFirstName()).
               thenReturn("UPDATED_NAME");
        Mockito.when(updateMock.getLastName())
               .thenReturn("UPDATED_LAST_NAME");
        Mockito.when(updateMock.isActive())
               .thenReturn(false);

        Courier updateRes1 = courierService.updateCourier(1L, updateMock);

        assertNotNull(updateRes1);
        assertEquals("UPDATED_NAME UPDATED_LAST_NAME", updateRes1.getName());
        assertFalse(updateRes1.isActive());
        Mockito.verify(courierRepositoryMock, Mockito.times(1)).findById(1L);
        Mockito.verify(courierRepositoryMock, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(updateMock, Mockito.times(1)).getFirstName();
        Mockito.verify(updateMock, Mockito.times(1)).getLastName();
        Mockito.verify(updateMock, Mockito.times(1)).isActive();

        CourierIsNotFound ex = assertThrows(CourierIsNotFound.class,
                                            () -> courierService.updateCourier(666L, updateMock)
                                           );
        assertNotNull(ex);
        Mockito.verify(courierRepositoryMock, Mockito.times(1)).findById(666L);
    }

}