package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.BookingDTO;
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
import java.util.List;

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
    void getBookmarksByUserId_ShouldReturnBookings_WhenFound() {
        int userId = 1;
        List<BookingDTO> mockBookings = new ArrayList<>();
        mockBookings.add(mock(BookingDTO.class)); // Add a mock BookingDTO

        when(bookingService.findByUserId(userId)).thenReturn(mockBookings);

        ResponseEntity<List<BookingDTO>> response = bookingController.getBookmarksByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBookings, response.getBody());
    }

    @Test
    void getBookmarksByUserId_ShouldReturnNotFound_WhenNoBookings() {
        int userId = 1;

        when(bookingService.findByUserId(userId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<BookingDTO>> response = bookingController.getBookmarksByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createBooking_ShouldReturnCreated_WhenBookingSuccessful() {
        Booking bookingRequest = new Booking();
        String expectedResponse = "Booking created successfully";

        when(bookingService.bookProperty(bookingRequest)).thenReturn(expectedResponse);

        ResponseEntity<String> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void createBooking_ShouldReturnConflict_WhenPropertyAlreadyBooked() {
        Booking bookingRequest = new Booking();
        when(bookingService.bookProperty(bookingRequest)).thenThrow(new PropertyAlreadyBookedException("Property already booked"));

        ResponseEntity<String> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Property already booked", response.getBody());
    }

    @Test
    void createBooking_ShouldReturnNotFound_WhenPropertyOrUserNotFound() {
        Booking bookingRequest = new Booking(); // Create a valid Booking object
        when(bookingService.bookProperty(bookingRequest)).thenThrow(new PropertyNotFoundException("Property not found"));

        ResponseEntity<String> response = bookingController.createBooking(bookingRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Property not found", response.getBody());
    }
}
