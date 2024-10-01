package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Service.BookmarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookmarkControllerTest {

    @Mock
    private BookmarkService bookmarkService;

    @InjectMocks
    private BookmarkController bookmarkController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getBookmarksByUserId_WhenBookmarksExist() {
        int userId = 1;

        BookmarkDTO bookmark1 = mock(BookmarkDTO.class);
        BookmarkDTO bookmark2 = mock(BookmarkDTO.class);

        List<BookmarkDTO> bookmarkDTOList = Arrays.asList(bookmark1, bookmark2);
        when(bookmarkService.findByUser(userId)).thenReturn(bookmarkDTOList);

        ResponseEntity<List<BookmarkDTO>> response = bookmarkController.getBookmarksByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(bookmarkService, times(1)).findByUser(userId);
    }

    @Test
    void getBookmarksByUserId_WhenNoBookmarksExist() {

        int userId = 1;
        when(bookmarkService.findByUser(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<BookmarkDTO>> response = bookmarkController.getBookmarksByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookmarkService, times(1)).findByUser(userId);
    }

    @Test
    void saveBookmark_WhenBookmarkIsSaved() {

        AppUser mockUser = new AppUser();
        mockUser.setUserId(1);

        Property mockProperty = new Property();
        mockProperty.setPropertyId(100);

        Bookmark bookmark = new Bookmark(1, mockUser, mockProperty);
        when(bookmarkService.saveBookmark(bookmark)).thenReturn(true);

        ResponseEntity<Map<String, String>> response = bookmarkController.saveBookmark(bookmark);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Bookmark saved successfully.", response.getBody().get("message"));
        verify(bookmarkService, times(1)).saveBookmark(bookmark);
    }


    @Test
    void saveBookmark_WhenBookmarkAlreadyExists() {

        AppUser mockUser = new AppUser();
        mockUser.setUserId(1);

        Property mockProperty = new Property();
        mockProperty.setPropertyId(100);

        Bookmark bookmark = new Bookmark(1, mockUser, mockProperty);
        when(bookmarkService.saveBookmark(bookmark)).thenReturn(false);

        ResponseEntity<Map<String, String>> response = bookmarkController.saveBookmark(bookmark);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Bookmark already exists.", response.getBody().get("message"));
        verify(bookmarkService, times(1)).saveBookmark(bookmark);
    }

}
