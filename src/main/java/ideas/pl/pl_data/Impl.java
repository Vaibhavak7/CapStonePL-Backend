package ideas.pl.pl_data;

import ideas.pl.pl_data.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Impl {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PropertyRepository propertyRepository;


    public static void main(String[] args) {
        SpringApplication.run(Impl.class, args);
    }

}
