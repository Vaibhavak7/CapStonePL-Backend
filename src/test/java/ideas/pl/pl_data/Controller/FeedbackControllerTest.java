package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedbackControllerTest {

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    private FeedbackDTO feedbackDTO;
    private Feedback feedback;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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
                return "Great property!";
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

        feedback = new Feedback();
        feedback.setRating(5);
        feedback.setComment("Great property!");
        feedback.setPostedDate(LocalDateTime.now());
    }

    @Test
    void getFeedbacks() {
        List<FeedbackDTO> feedbackList = Arrays.asList(feedbackDTO);
        when(feedbackService.findBy()).thenReturn(feedbackList);

        List<FeedbackDTO> result = feedbackController.getFeedbacks();

        assertEquals(1, result.size());
        assertEquals("Great property!", result.get(0).getComment());
        verify(feedbackService, times(1)).findBy();
    }

    @Test
    void createFeedback_Success() {
        when(feedbackService.createFeedback(any(Feedback.class))).thenReturn(true);

        ResponseEntity<Map<String, String>> response = feedbackController.createFeedback(feedback);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Review saved successfully.", response.getBody().get("message"));
        verify(feedbackService, times(1)).createFeedback(any(Feedback.class));
    }

    @Test
    void createFeedback_Conflict() {
        when(feedbackService.createFeedback(any(Feedback.class))).thenReturn(false);

        ResponseEntity<Map<String, String>> response = feedbackController.createFeedback(feedback);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Review already exists.", response.getBody().get("message"));
        verify(feedbackService, times(1)).createFeedback(any(Feedback.class));
    }

    @Test
    void getFeedbackById_Found() {
        when(feedbackService.getFeedbackById(1)).thenReturn(Optional.of(feedbackDTO));

        ResponseEntity<FeedbackDTO> response = feedbackController.getFeedbackById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Great property!", response.getBody().getComment());
        verify(feedbackService, times(1)).getFeedbackById(1);
    }

    @Test
    void getFeedbackById_NotFound() {
        when(feedbackService.getFeedbackById(1)).thenReturn(Optional.empty());

        ResponseEntity<FeedbackDTO> response = feedbackController.getFeedbackById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(feedbackService, times(1)).getFeedbackById(1);
    }

    @Test
    void getFeedbackByPropertyId_Found() {
        List<FeedbackDTO> feedbackList = Arrays.asList(feedbackDTO);
        when(feedbackService.findFeedbackByPropertyId(101)).thenReturn(feedbackList);

        ResponseEntity<List<FeedbackDTO>> response = feedbackController.getFeedbackByPropertyId(101);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Great property!", response.getBody().get(0).getComment());
        verify(feedbackService, times(1)).findFeedbackByPropertyId(101);
    }

    @Test
    void getFeedbackByPropertyId_NotFound() {
        when(feedbackService.findFeedbackByPropertyId(101)).thenReturn(Arrays.asList());

        ResponseEntity<List<FeedbackDTO>> response = feedbackController.getFeedbackByPropertyId(101);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(feedbackService, times(1)).findFeedbackByPropertyId(101);
    }

    @Test
    void getAverageRatingByPropertyId() {
        when(feedbackService.getAverageRatingByPropertyId(101)).thenReturn(4.5);

        Double avgRating = feedbackController.getAverageRatingByPropertyId(101);

        assertEquals(4.5, avgRating);
        verify(feedbackService, times(1)).getAverageRatingByPropertyId(101);
    }

    @Test
    void updateFeedback() {
        when(feedbackService.updateFeedback(anyInt(), any(Feedback.class))).thenReturn(feedback);

        ResponseEntity<Feedback> response = feedbackController.updateFeedback(1, feedback);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(feedbackService, times(1)).updateFeedback(anyInt(), any(Feedback.class));
    }

    @Test
    void deleteFeedback() {
        doNothing().when(feedbackService).deleteFeedback(1);

        ResponseEntity<Void> response = feedbackController.deleteFeedback(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(feedbackService, times(1)).deleteFeedback(1);
    }
}
