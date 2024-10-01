package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.Entity.Booking;
import ideas.pl.pl_data.Exception.PropertyAlreadyBookedException;
import ideas.pl.pl_data.Exception.PropertyNotFoundException;
import ideas.pl.pl_data.Exception.UserNotFoundException;
import ideas.pl.pl_data.Service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_ShouldReturnCreated_WhenBookingSuccessful() {
        Booking bookingRequest = new Booking();
        String expectedResponse = "Booking created successfully";

        when(bookingService.bookProperty(bookingRequest)).thenReturn(expectedResponse);

        ResponseEntity<Map<String, String>> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Map<String, String> expectedBody = new HashMap<>();
        expectedBody.put("message", expectedResponse);
        assertEquals(expectedBody, response.getBody());
    }

    @Test
    void createBooking_ShouldReturnConflict_WhenPropertyAlreadyBooked() {
        Booking bookingRequest = new Booking();
        when(bookingService.bookProperty(bookingRequest)).thenThrow(new PropertyAlreadyBookedException("Property already booked"));

        ResponseEntity<Map<String, String>> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createBooking_ShouldReturnNotFound_WhenPropertyOrUserNotFound() {
        Booking bookingRequest = new Booking();
        when(bookingService.bookProperty(bookingRequest)).thenThrow(new PropertyNotFoundException("Property not found"));

        ResponseEntity<Map<String, String>> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createBooking_ShouldReturnNotFound_WhenUserNotFound() {
        Booking bookingRequest = new Booking();
        when(bookingService.bookProperty(bookingRequest)).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<Map<String, String>> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
