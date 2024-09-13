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
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
////
//        List<Feedback> byId = feedbackRepository.findBy();
//        for(Feedback p: byId)
//        {
//            System.out.println(p.getRating());
//        }
////
////        Optional<PropertyDTO> optionalPropertyDTO = propertyRepository.findById(101);
////
////        if (optionalPropertyDTO.isPresent()) {
////            PropertyDTO propertyDTO = optionalPropertyDTO.get();
////            System.out.println(propertyDTO.getPropertyName());
////        } else {
////            System.out.println("Property not found.");
////        }
////
////
////
////    }
////
//}
}
