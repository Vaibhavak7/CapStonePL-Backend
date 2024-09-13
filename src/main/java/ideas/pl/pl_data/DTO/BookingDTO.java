package ideas.pl.pl_data.DTO;

import java.time.LocalDate;

public interface BookingDTO {
    public int getBookingId();
    public LocalDate getStartDate();
    public LocalDate getEndDate();

    public Property getProperty();
    public AppUser getUser();

    interface AppUser{
        public int getUserId();
        public String getUserName();
    }

    interface Property{
        public int getPropertyId();
        public String getPropertyName();
    }
}
