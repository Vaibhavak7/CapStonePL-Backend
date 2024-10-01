package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Bookmark;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Exception.ResourceNotFoundException;
import ideas.pl.pl_data.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public boolean createFeedback(Feedback feedback) {
        List<FeedbackDTO> existingFeedback = feedbackRepository.findByProperty_PropertyIdAndUser_UserId(feedback.getProperty().getPropertyId(), feedback.getUser().getUserId());
        int exists = existingFeedback.size();
        if (exists!=0) {
            return false;
        }

        feedbackRepository.save(feedback);
        return true;
    }


    public List<FeedbackDTO> findBy() {
        return feedbackRepository.findBy();
    }

    public Optional<FeedbackDTO> getFeedbackById(int feedbackId) {
        return feedbackRepository.findByFeedbackId(feedbackId);
    }
    public List<FeedbackDTO> findFeedbackByPropertyId(int feedbackId) {
        return feedbackRepository.findByProperty_PropertyId(feedbackId);
    }

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

    public void deleteFeedback(int feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public Double getAverageRatingByPropertyId(int PropertyId) {
        return feedbackRepository.findAverageRatingByPropertyId(PropertyId);}
}
