package ideas.pl.pl_data.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int propertyId;

    @ManyToOne
    @JoinColumn(name = "owner_Id")
    private Owner owner;

    @OneToMany(mappedBy = "property",fetch = FetchType.EAGER)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "property",fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "property",fetch = FetchType.EAGER)
    private List<Bookmark> bookmarks;

    private String propertyName;
    private String address1;
    private String address2;
    private String area;
    private String city;
    private String state;
    private String zipcode;
    private String typeOfProperty;
    @Column(length = 600)
    private String description;
    private float rent;
    private float securityDeposit;
    private int howOldProperty;
    private LocalDateTime postedOn;
    private String features;
    private String imageUrl;
    private int maxGuests;

    public Property() {

    }

    public Property(int propertyId, Owner owner, List<Feedback> feedbacks, List<Booking> bookings, List<Bookmark> bookmarks, String propertyName, String address1, String address2, String area,
                    String city, String state, String zipcode, String typeOfProperty, String description,
                    float rent, float securityDeposit, int howOldProperty, LocalDateTime postedOn,
                    String features, String imageUrl, int maxGuests) {
        this.propertyId = propertyId;
        this.owner = owner;
        this.feedbacks = feedbacks;
        this.bookings = bookings;
        this.bookmarks = bookmarks;
        this.propertyName = propertyName;
        this.address1 = address1;
        this.address2 = address2;
        this.area = area;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.typeOfProperty = typeOfProperty;
        this.description = description;
        this.rent = rent;
        this.securityDeposit = securityDeposit;
        this.howOldProperty = howOldProperty;
        this.postedOn = postedOn;
        this.features = features;
        this.imageUrl = imageUrl;
        this.maxGuests = maxGuests;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTypeOfProperty() {
        return typeOfProperty;
    }

    public void setTypeOfProperty(String typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRent() {
        return rent;
    }

    public void setRent(float rent) {
        this.rent = rent;
    }

    public float getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(float securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public int getHowOldProperty() {
        return howOldProperty;
    }

    public void setHowOldProperty(int howOldProperty) {
        this.howOldProperty = howOldProperty;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
//                ", feedbacks=" + feedbacks +
//                ", bookings=" + bookings +
//                ", bookmarks=" + bookmarks +
                ", propertyName='" + propertyName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", typeOfProperty='" + typeOfProperty + '\'' +
                ", description='" + description + '\'' +
                ", rent=" + rent +
                ", securityDeposit=" + securityDeposit +
                ", howOldProperty=" + howOldProperty +
                ", postedOn=" + postedOn +
                ", features='" + features + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", maxGuests=" + maxGuests +
                '}';
//        return "Property{" +
//                "propertyId=" + propertyId +
//                ", owner=" + owner +
//                ", feedbacks=" + feedbacks +
//                ", bookings=" + bookings +
//                ", bookmarks=" + bookmarks +
//                ", propertyName='" + propertyName + '\'' +
//                ", address1='" + address1 + '\'' +
//                ", address2='" + address2 + '\'' +
//                ", area='" + area + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", zipcode='" + zipcode + '\'' +
//                ", typeOfProperty='" + typeOfProperty + '\'' +
//                ", description='" + description + '\'' +
//                ", rent=" + rent +
//                ", securityDeposit=" + securityDeposit +
//                ", howOldProperty=" + howOldProperty +
//                ", postedOn=" + postedOn +
//                ", features='" + features + '\'' +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", maxGuests=" + maxGuests +
//                '}';
    }
}
