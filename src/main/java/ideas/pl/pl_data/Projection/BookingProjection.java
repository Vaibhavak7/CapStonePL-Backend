package ideas.pl.pl_data.Projection;

public interface BookingProjection {
    int getFeedbackId();
    String getComments();  // Only retrieve comments
    int getRating();
}
