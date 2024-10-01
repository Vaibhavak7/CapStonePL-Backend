package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Service.FeedbackService;
import ideas.pl.pl_data.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    //TODO:Get all feedbacks
    @GetMapping
    public List<FeedbackDTO> getFeedbacks() {
        return feedbackService.findBy();
    }

    //TODO:Create Feedback
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createFeedback(@RequestBody Feedback feedback) {
        boolean isSaved = feedbackService.createFeedback(feedback);
        Map<String, String> response = new HashMap<>();
        if (isSaved) {
            response.put("message", "Review saved successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Review already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }


    //TODO:Get a feedback by ID
    @GetMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable int feedbackId) {
        Optional<FeedbackDTO> feedback = feedbackService.getFeedbackById(feedbackId);
        return feedback.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    //TODO:Get FeedBack by PropertyId
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByPropertyId(@PathVariable int propertyId) {
        List<FeedbackDTO> feedbacks = feedbackService.findFeedbackByPropertyId(propertyId);
        if (feedbacks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(feedbacks);
    }

    //TODO:Get Avg of Feedback for property
    @GetMapping("/Property/{PropertyId}/avg")
    public Double getAverageRatingByPropertyId(@PathVariable int PropertyId) {
        return feedbackService.getAverageRatingByPropertyId(PropertyId);}


    //TODO:Update a feedback
    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable int feedbackId, @RequestBody Feedback updatedFeedback) {
        Feedback feedback = feedbackService.updateFeedback(feedbackId, updatedFeedback);
        return ResponseEntity.ok(feedback);
    }

    //TODO:Delete a feedback
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }
}
