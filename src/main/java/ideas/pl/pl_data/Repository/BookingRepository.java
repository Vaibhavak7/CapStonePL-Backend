package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.Entity.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking,Integer> {
}
