package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking,Integer> {

    @Query("SELECT b FROM Booking b WHERE b.user.userId = :id")
    List<BookingDTO> findByuserId(@Param("id") int id);
}
