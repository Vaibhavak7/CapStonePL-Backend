package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.DTO.BookingRequestDTO;
import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Booking;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Exception.PropertyAlreadyBookedException;
import ideas.pl.pl_data.Exception.PropertyNotFoundException;
import ideas.pl.pl_data.Exception.UserNotFoundException;
import ideas.pl.pl_data.Projection.AppUserProjection;
import ideas.pl.pl_data.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PropertyService propertyService;

    @Autowired
    UserService userService;

    public List<BookingDTO> findByUserId(int id) {
        return bookingRepository.findByUser_UserId(id);
    }

    public List<BookingDTO> findByPropertyId(int id) {
        return bookingRepository.findByProperty_PropertyId(id);
    }

    public String bookProperty(Booking bookingRequestDTO) {
        // Check for existing bookings for the same property
        List<Booking> existingBookings = bookingRepository.findByPropertyId(
                bookingRequestDTO.getProperty().getPropertyId(),
                bookingRequestDTO.getStartDate(),
                bookingRequestDTO.getEndDate());

        if (!existingBookings.isEmpty()) {
            throw new PropertyAlreadyBookedException("Property is already booked during these dates");
        }
        bookingRepository.save(bookingRequestDTO);

        return "Booking successful";
    }


}
