package ideas.pl.pl_data.Projection;

import ideas.pl.pl_data.Entity.Property;

import java.time.LocalDate;
import java.util.List;

public interface AppUserProjection {
    int getUserId();
    String getUserName();
    String getEmail();
    public String getPassword();

    List<FeedbackProjection> getFeedbacks();
    List<BookingProjection> getBookings();
    List<BookmarkProjection> getBookmarks();

    interface FeedbackProjection{
        int getFeedbackId();
        String getComment();
    }
    interface BookingProjection{
        public int getBookingId();
        public LocalDate getStartDate();
        public LocalDate getEndDate();
    }
    interface BookmarkProjection{
        public int getBookmarkId();
        public Property getProperty();
        interface Property{
            public int getPropertyId();
        }
    }
}
