package ideas.pl.pl_data.Repository;

// BookingRepository.java
import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.Entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

//    @Query("SELECT b FROM Booking b WHERE b.user.userId = :id")
//    List<BookingDTO> findByuserId(@Param("id") int id);

    List<BookingDTO> findByUser_UserId(int userId);


    List<BookingDTO> findByProperty_PropertyId(int propertyId);

    @Query("SELECT b FROM Booking b WHERE b.property.propertyId = :propertyId " +
            "AND ((b.startDate < :endDate AND b.endDate > :startDate) OR " +
            "(b.startDate = :endDate))")
    List<Booking> findByPropertyId(@Param("propertyId") int propertyId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);

//    List<Booking> findByPropertyPropertyIdAndStartDateLessThanAndEndDateGreaterThanOrStartDate(int propertyId, LocalDate startDate, LocalDate endDate);


}
