package ideas.pl.pl_data.Entity;

import static org.junit.jupiter.api.Assertions.*;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Exception.ResourceNotFoundException;
import ideas.pl.pl_data.Repository.FeedbackRepository;
import ideas.pl.pl_data.Service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class FeedbackTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback feedback;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AppUser user = new AppUser();
        user.setUserId(1);

        Property property = new Property();
        property.setPropertyId(101);

        feedback = new Feedback();
        feedback.setFeedbackId(1);
        feedback.setUser(user);
        feedback.setProperty(property);
        feedback.setRating(5);
        feedback.setComment("Great place!");
        feedback.setPostedDate(LocalDateTime.now());
    }

    @Test
    void createFeedback_ShouldReturnCreatedFeedback() {
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback createdFeedback = feedbackService.createFeedback(feedback);

        assertNotNull(createdFeedback);
        assertEquals(1, createdFeedback.getFeedbackId());
        assertEquals("Great place!", createdFeedback.getComment());
        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void getFeedbackById_ShouldReturnFeedback_WhenFound() {
        when(feedbackRepository.findById(1)).thenReturn(Optional.ofNullable(feedback));

        Optional<FeedbackDTO> foundFeedback = feedbackService.getFeedbackById(1);

//        assertTrue(foundFeedback.isPresent());
        assertEquals(1, foundFeedback.get().getFeedbackId());
    }

    @Test
    void getFeedbackById_ShouldThrowException_WhenNotFound() {
        when(feedbackRepository.findFeedbackById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> feedbackService.getFeedbackById(1));
    }

    @Test
    void updateFeedback_ShouldReturnUpdatedFeedback() {
        Feedback updatedFeedback = new Feedback();
        updatedFeedback.setRating(4);
        updatedFeedback.setComment("Nice place!");

        when(feedbackRepository.findById(1)).thenReturn(Optional.of(feedback));
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(updatedFeedback);

        Feedback result = feedbackService.updateFeedback(1, updatedFeedback);

        assertNotNull(result);
        assertEquals(4, result.getRating());
        assertEquals("Nice place!", result.getComment());
        verify(feedbackRepository, times(1)).save(updatedFeedback);
    }

    @Test
    void deleteFeedback_ShouldInvokeRepositoryDelete() {
        when(feedbackRepository.findById(1)).thenReturn(Optional.of(feedback));

        feedbackService.deleteFeedback(1);

        verify(feedbackRepository, times(1)).deleteById(1);
    }
}
