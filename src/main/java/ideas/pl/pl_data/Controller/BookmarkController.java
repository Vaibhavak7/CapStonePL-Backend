package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/properties/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    // Get all bookmarks for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookmarkDTO>> getBookmarksByUserId(@PathVariable int userId) {
        List<BookmarkDTO> bookmarks = bookmarkService.findByUser(userId);
        if (bookmarks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(bookmarks);
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> saveBookmark(@RequestBody Bookmark bookmark) {
        boolean isSaved = bookmarkService.saveBookmark(bookmark);
        Map<String, String> response = new HashMap<>();
        if (isSaved) {
            response.put("message", "Bookmark saved successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Bookmark already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }


}
