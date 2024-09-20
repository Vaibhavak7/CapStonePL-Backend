package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.BookingDTO;
import ideas.pl.pl_data.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    public List<BookingDTO> findByUserId(int id)
    {
        return bookingRepository.findByuserId(id);
    }

}
