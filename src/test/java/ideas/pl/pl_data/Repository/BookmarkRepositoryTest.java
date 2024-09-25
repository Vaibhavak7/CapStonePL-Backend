package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Entity.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookmarkRepositoryTest {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    private AppUser user;
    private Property property;

    @BeforeEach
    void setUp() {
        // Create and save test user
        user = new AppUser();
        user.setUserName("testUser");
        user.setEmail("testuser@example.com");
        appUserRepository.save(user);

        // Create and save test property
        property = new Property();
        property.setPropertyName("Test Property");
        property.setArea("Test Location");
        propertyRepository.save(property);
    }

    @Test
    @Rollback
    public void testFindByUserId() {
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setProperty(property);
        bookmarkRepository.save(bookmark);

        List<BookmarkDTO> bookmarks = bookmarkRepository.findByUser_UserId(user.getUserId());
//        assertEquals(1, bookmarks.size());
//        assertEquals(property.getPropertyId(), bookmarks.get(0).getProperty().getPropertyId());
    }

    @Test
    @Rollback
    public void testFindByUser_UserId() {
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setProperty(property);
        bookmarkRepository.save(bookmark);

        List<BookmarkDTO> bookmarks = bookmarkRepository.findByUser_UserId(user.getUserId());
        assertEquals(1, bookmarks.size());
        assertEquals(property.getPropertyId(), bookmarks.get(0).getProperty().getPropertyId());
    }

    @Test
    @Rollback
    public void testFindByUser_UserIdAndProperty_PropertyId() {
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setProperty(property);
        bookmarkRepository.save(bookmark);

        Bookmark foundBookmark = bookmarkRepository.findByUser_UserIdAndProperty_PropertyId(user.getUserId(), property.getPropertyId()).orElse(null);
        assertEquals(user.getUserId(), foundBookmark.getUser().getUserId());
        assertEquals(property.getPropertyId(), foundBookmark.getProperty().getPropertyId());
    }


}
