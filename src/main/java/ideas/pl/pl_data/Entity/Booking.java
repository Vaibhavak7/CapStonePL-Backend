package ideas.pl.pl_data.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "property_Id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private AppUser user;

    private LocalDate startDate;
    private LocalDate endDate;

    public Booking() {

    }

    public Booking(int bookingId, Property property, AppUser user, LocalDate startDate, LocalDate endDate) {
        this.bookingId = bookingId;
        this.property = property;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "\nBooking [1bookingId=" + bookingId +
                ", startDate=" + startDate + ", endDate=" + endDate + "]\n";
//        return "\nBooking [bookingId=" + bookingId + ", property=" + property + ", user=" + user +
//                ", startDate=" + startDate + ", endDate=" + endDate + "]\n";
    }
}
