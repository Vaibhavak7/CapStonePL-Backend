package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> {

    @Query("SELECT b FROM Bookmark b WHERE b.user.userId = :id")
    List<BookmarkDTO> findByUserId(@Param("id") int id);
}
