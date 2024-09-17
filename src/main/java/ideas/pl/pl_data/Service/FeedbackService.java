package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Exception.ResourceNotFoundException;
import ideas.pl.pl_data.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Create a new feedback
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Get all feedbacks
    public List<FeedbackDTO> findBy() {
        return feedbackRepository.findBy();
    }

    // Get a feedback by ID
    public Optional<FeedbackDTO> getFeedbackById(int feedbackId) {
        return feedbackRepository.findFeedbackById(feedbackId);
    }
    public List<FeedbackDTO> findFeedbackByPropertyId(int feedbackId) {
        return feedbackRepository.findFeedbackByPropertyId(feedbackId);
    }


    // Update a feedback
    public Feedback updateFeedback(int feedbackId, Feedback updatedFeedback) {
        return feedbackRepository.findById(feedbackId).map(feedback -> {
            feedback.setRating(updatedFeedback.getRating());
            feedback.setComment(updatedFeedback.getComment());
            feedback.setPostedDate(updatedFeedback.getPostedDate());
            feedback.setProperty(updatedFeedback.getProperty());
            feedback.setUser(updatedFeedback.getUser());
            return feedbackRepository.save(feedback);
        }).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
    }

    // Delete a feedback
    public void deleteFeedback(int feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
