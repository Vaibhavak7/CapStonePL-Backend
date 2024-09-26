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

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/properties/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookmarksByUserId(@PathVariable int userId) {
        List<BookingDTO> bookings = bookingService.findByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<BookingDTO>> getBookmarksByPropertyId(@PathVariable int propertyId) {
        List<BookingDTO> bookings = bookingService.findByPropertyId(propertyId);
        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Booking bookingRequestDTO) {
        try {
            String response = bookingService.bookProperty(bookingRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (PropertyAlreadyBookedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (PropertyNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
