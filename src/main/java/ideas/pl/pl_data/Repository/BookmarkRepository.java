package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.Entity.Bookmark;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark,Integer> {
}
