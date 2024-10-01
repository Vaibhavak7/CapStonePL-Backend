package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Entity.Booking;
import ideas.pl.pl_data.Entity.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private AppUserRepository userRepository;

    private AppUser user;
    private Property property;

    @BeforeEach
    void setUp() {
        user = new AppUser();
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user = userRepository.save(user);

        property = new Property();
        property.setPropertyName("Test Property");
        property = propertyRepository.save(property);


        Booking booking1 = new Booking();
        booking1.setUser(user);
        booking1.setProperty(property);
        booking1.setStartDate(LocalDate.of(2023, 10, 1));
        booking1.setEndDate(LocalDate.of(2023, 10, 10));

        Booking booking2 = new Booking();
        booking2.setUser(user);
        booking2.setProperty(property);
        booking2.setStartDate(LocalDate.of(2023, 11, 1));
        booking2.setEndDate(LocalDate.of(2023, 11, 5));


        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
    }

    @Test
    void findByUser_UserId() {

        List<BookingDTO> bookings = bookingRepository.findByUser_UserId(user.getUserId());

        assertNotNull(bookings);
        assertEquals(2, bookings.size());
    }

    @Test
    void findByPropertyId() {
        int propertyId = property.getPropertyId();
        LocalDate startDate = LocalDate.of(2023, 10, 5);
        LocalDate endDate = LocalDate.of(2023, 10, 15);

        List<Booking> bookings = bookingRepository.findByPropertyId(propertyId, startDate, endDate);

        assertNotNull(bookings);
        assertEquals(1, bookings.size());
    }
}
