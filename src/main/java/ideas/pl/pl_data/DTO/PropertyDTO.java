package ideas.pl.pl_data.DTO;

import ideas.pl.pl_data.Entity.Booking;
import ideas.pl.pl_data.Entity.Owner;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyDTO {

//    public List<Booking> getBookings();
//
//
//    public List<Bookmark> getBookmarks();
//
//
//    public List<Feedback> getFeedbacks();


    public int getPropertyId();

    public Owner getOwner();

    interface Owner{
        public int getOwnerId();
        public String getOwnerName();
    }

    public String getPropertyName();

    public String getAddress1();

    public String getAddress2();

    public String getArea();

    public String getCity();

    public String getState();

    public String getZipcode();

    public String getTypeOfProperty();

    public String getDescription();

    public float getRent();

    public float getSecurityDeposit();

    public int getHowOldProperty();

    public LocalDateTime getPostedOn();

    public String getFeatures();

    public String getImageUrl();

    public int getMaxGuests();


}

