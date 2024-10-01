package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> {

    List<BookmarkDTO> findByUser_UserId(int userId);

    Optional<Bookmark> findByUser_UserIdAndProperty_PropertyId(int userId, int propertyId);


}
