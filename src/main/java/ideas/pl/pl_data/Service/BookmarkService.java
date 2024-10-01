package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<BookmarkDTO> findByUser(int id) {
        return bookmarkRepository.findByUser_UserId(id);
    }
    public boolean saveBookmark(Bookmark bookmark) {
        Optional<Bookmark> bookmarks = bookmarkRepository.findByUser_UserIdAndProperty_PropertyId(bookmark.getUser().getUserId(), bookmark.getProperty().getPropertyId());
        boolean exists = bookmarks.isPresent();
        if (exists) {
            return false;
        }

        bookmarkRepository.save(bookmark);
        return true;
    }

    public boolean deleteBookmark(int userId, int propertyId) {
        Optional<Bookmark> optionalBookmark = bookmarkRepository.findByUser_UserIdAndProperty_PropertyId(userId, propertyId);

        if (optionalBookmark.isPresent()) {
            Bookmark bookmark = optionalBookmark.get();

            bookmarkRepository.delete(bookmark);

            return true;
        } else {
            return false;
        }
    }


}
