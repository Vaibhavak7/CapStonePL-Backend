package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback feedback;
    private FeedbackDTO feedbackDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        feedback = new Feedback();
        feedback.setRating(5);
        feedback.setComment("Amazing place!");
        feedback.setPostedDate(LocalDateTime.now());

        feedbackDTO = new FeedbackDTO() {
            @Override
            public int getFeedbackId() {
                return 1;
            }

            @Override
            public int getRating() {
                return 5;
            }

            @Override
            public String getComment() {
                return "Amazing place!";
            }

            @Override
            public LocalDateTime getPostedDate() {
                return LocalDateTime.now();
            }

            @Override
            public AppUser getUser() {
                return new AppUser() {
                    @Override
                    public int getUserId() {
                        return 1;
                    }

                    @Override
                    public String getUserName() {
                        return "John Doe";
                    }
                };
            }

            @Override
            public Property getProperty() {
                return new Property() {
                    @Override
                    public int getPropertyId() {
                        return 101;
                    }

                    @Override
                    public String getPropertyName() {
                        return "Luxury Villa";
                    }
                };
            }
        };
    }

    @Test
    void createFeedback() {

        Property property = new Property();
        property.setPropertyId(1);

        Feedback feedback = new Feedback();
        feedback.setProperty(property);
        feedback.setUser(new AppUser());

        when(feedbackRepository.findByProperty_PropertyIdAndUser_UserId(anyInt(), anyInt()))
                .thenReturn(Arrays.asList());

        boolean result = feedbackService.createFeedback(feedback);

        assertTrue(result);
        verify(feedbackRepository, times(1)).save(feedback);
    }

    

    @Test
    void findBy() {
        List<FeedbackDTO> feedbackList = Arrays.asList(feedbackDTO);
        when(feedbackRepository.findBy()).thenReturn(feedbackList);

        List<FeedbackDTO> result = feedbackService.findBy();

        assertEquals(1, result.size());
        assertEquals("Amazing place!", result.get(0).getComment());
        verify(feedbackRepository, times(1)).findBy();
    }

    @Test
    void getFeedbackById_Found() {
        when(feedbackRepository.findByFeedbackId(1)).thenReturn(Optional.of(feedbackDTO));

        Optional<FeedbackDTO> result = feedbackService.getFeedbackById(1);

        assertTrue(result.isPresent());
        assertEquals("Amazing place!", result.get().getComment());
        verify(feedbackRepository, times(1)).findByFeedbackId(1);
    }

    @Test
    void getFeedbackById_NotFound() {
        when(feedbackRepository.findByFeedbackId(1)).thenReturn(Optional.empty());

        Optional<FeedbackDTO> result = feedbackService.getFeedbackById(1);

        assertFalse(result.isPresent());
        verify(feedbackRepository, times(1)).findByFeedbackId(1);
    }

    @Test
    void findFeedbackByPropertyId() {
        List<FeedbackDTO> feedbackList = Arrays.asList(feedbackDTO);
        when(feedbackRepository.findByProperty_PropertyId(101)).thenReturn(feedbackList);

        List<FeedbackDTO> result = feedbackService.findFeedbackByPropertyId(101);

        assertEquals(1, result.size());
        assertEquals("Amazing place!", result.get(0).getComment());
        verify(feedbackRepository, times(1)).findByProperty_PropertyId(101);
    }


    @Test
    void deleteFeedback() {
        doNothing().when(feedbackRepository).deleteById(1);

        feedbackService.deleteFeedback(1);

        verify(feedbackRepository, times(1)).deleteById(1);
    }

    @Test
    void getAverageRatingByPropertyId() {
        when(feedbackRepository.findAverageRatingByPropertyId(101)).thenReturn(4.5);

        Double avgRating = feedbackService.getAverageRatingByPropertyId(101);

        assertEquals(4.5, avgRating);
        verify(feedbackRepository, times(1)).findAverageRatingByPropertyId(101);
    }
}
