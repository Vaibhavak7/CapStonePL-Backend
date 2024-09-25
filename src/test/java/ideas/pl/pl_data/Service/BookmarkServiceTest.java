package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Repository.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUser_WhenBookmarksExist() {
        // Arrange
        int userId = 1;
        BookmarkDTO mockBookmark1 = mock(BookmarkDTO.class);
        BookmarkDTO mockBookmark2 = mock(BookmarkDTO.class);
        List<BookmarkDTO> mockBookmarks = Arrays.asList(mockBookmark1, mockBookmark2);
        when(bookmarkRepository.findByUserId(userId)).thenReturn(mockBookmarks);

        // Act
        List<BookmarkDTO> bookmarks = bookmarkService.findByUser(userId);

        // Assert
        assertNotNull(bookmarks);
        assertEquals(2, bookmarks.size());
        verify(bookmarkRepository, times(1)).findByUserId(userId);
    }

    @Test
    void findByUser_WhenNoBookmarksExist() {
        // Arrange
        int userId = 1;
        when(bookmarkRepository.findByUserId(userId)).thenReturn(List.of());

        // Act
        List<BookmarkDTO> bookmarks = bookmarkService.findByUser(userId);

        // Assert
        assertNotNull(bookmarks);
        assertEquals(0, bookmarks.size());
        verify(bookmarkRepository, times(1)).findByUserId(userId);
    }

    @Test
    void saveBookmark_WhenBookmarkDoesNotExist() {
        // Arrange
        AppUser mockUser = new AppUser();
        mockUser.setUserId(1);
        Property mockProperty = new Property();
        mockProperty.setPropertyId(100);

        Bookmark bookmark = new Bookmark(1, mockUser, mockProperty);
        when(bookmarkRepository.findByUser_UserIdAndProperty_PropertyId(mockUser.getUserId(), mockProperty.getPropertyId()))
                .thenReturn(Optional.empty());

        // Act
        boolean result = bookmarkService.saveBookmark(bookmark);

        // Assert
        assertTrue(result);
        verify(bookmarkRepository, times(1)).findByUser_UserIdAndProperty_PropertyId(mockUser.getUserId(), mockProperty.getPropertyId());
        verify(bookmarkRepository, times(1)).save(bookmark);
    }

    @Test
    void saveBookmark_WhenBookmarkAlreadyExists() {
        // Arrange
        AppUser mockUser = new AppUser();
        mockUser.setUserId(1);
        Property mockProperty = new Property();
        mockProperty.setPropertyId(100);

        Bookmark bookmark = new Bookmark(1, mockUser, mockProperty);
        when(bookmarkRepository.findByUser_UserIdAndProperty_PropertyId(mockUser.getUserId(), mockProperty.getPropertyId()))
                .thenReturn(Optional.of(bookmark));

        // Act
        boolean result = bookmarkService.saveBookmark(bookmark);

        // Assert
        assertFalse(result);
        verify(bookmarkRepository, times(1)).findByUser_UserIdAndProperty_PropertyId(mockUser.getUserId(), mockProperty.getPropertyId());
        verify(bookmarkRepository, times(0)).save(bookmark);
    }
}
