package ideas.pl.pl_data.DTO;

import java.time.LocalDateTime;

public interface FeedbackDTO {
    public int getFeedbackId();
    public int getRating();
    public String getComment();
    public LocalDateTime getPostedDate();

    public AppUser getUser();
    public Property getProperty();

    interface AppUser{
        public int getUserId();
        public String getUserName();
    }

    interface Property{
        public int getPropertyId();
        public String getPropertyName();
    }
}

