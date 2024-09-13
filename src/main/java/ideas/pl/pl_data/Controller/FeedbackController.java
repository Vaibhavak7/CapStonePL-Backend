package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Service.FeedbackService;
import ideas.pl.pl_data.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Get all feedbacks
    @GetMapping
    public List<FeedbackDTO> getFeedbacks() {
        return feedbackService.findBy();
    }

    // Create a new feedback
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Feedback newFeedback = feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(newFeedback, HttpStatus.CREATED);
    }

    // Get a feedback by ID
    @GetMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable int feedbackId) {
        Optional<FeedbackDTO> feedback = feedbackService.getFeedbackById(feedbackId);
        return feedback.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update a feedback
    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable int feedbackId, @RequestBody Feedback updatedFeedback) {
        Feedback feedback = feedbackService.updateFeedback(feedbackId, updatedFeedback);
        return ResponseEntity.ok(feedback);
    }

    // Delete a feedback
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }
}
