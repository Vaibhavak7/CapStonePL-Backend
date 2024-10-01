package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.DTO.BookingRequestDTO;
import ideas.pl.pl_data.Entity.Booking;
import ideas.pl.pl_data.Exception.PropertyAlreadyBookedException;
import ideas.pl.pl_data.Exception.PropertyNotFoundException;
import ideas.pl.pl_data.Exception.UserNotFoundException;
import ideas.pl.pl_data.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/property/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //TODO:GET Booking by UserId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable int userId) {
        List<BookingDTO> bookings = bookingService.findByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookings);
    }

    //TODO:GET Booking by PropertyId
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByPropertyId(@PathVariable int propertyId) {
        List<BookingDTO> bookings = bookingService.findByPropertyId(propertyId);
        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookings);
    }

    //TODO:Create Booking
    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createBooking(@RequestBody Booking bookingRequestDTO) {
        try {
            String response = bookingService.bookProperty(bookingRequestDTO);
            Map<String,String> booking=new HashMap<>();
            booking.put("message",response);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (PropertyAlreadyBookedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (PropertyNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
