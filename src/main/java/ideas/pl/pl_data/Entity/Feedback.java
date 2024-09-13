package ideas.pl.pl_data.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "property_Id")
    private Property property;

    private int rating;
    private String comment;
    private LocalDateTime postedDate;

    public Feedback() {

    }

    public Feedback(int feedbackId, AppUser user, Property property, int rating, String comment, LocalDateTime postedDate) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.property = property;
        this.rating = rating;
        this.comment = comment;
        this.postedDate = postedDate;
    }



    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "\nFeedback [feedbackId=" + feedbackId +
                ", rating=" + rating + ", comment=" + comment + ", postedDate=" + postedDate + "]\n";
//        return "\nFeedback [feedbackId=" + feedbackId + ", user=" + user + ", property=" + property +
//                ", rating=" + rating + ", comment=" + comment + ", postedDate=" + postedDate + "]\n";
    }
}
