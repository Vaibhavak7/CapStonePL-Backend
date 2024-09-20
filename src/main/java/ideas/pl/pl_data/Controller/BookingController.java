package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Service.BookingService;
import ideas.pl.pl_data.Service.BookmarkService;
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

    // Get all bookmarks for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookmarksByUserId(@PathVariable int userId) {
        List<BookingDTO> bookmarks = bookingService.findByUserId(userId);
        if (bookmarks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookmarks);
    }
}