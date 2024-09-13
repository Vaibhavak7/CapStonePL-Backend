package ideas.pl.pl_data.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ownerId;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Property> properties;

    private String ownerName;
    private String contactInformation;
    private String email;
    private LocalDateTime timestamp;

    public Owner() {

    }

    public Owner(int ownerId, List<Property> properties, String ownerName, String contactInformation, String email, LocalDateTime timestamp) {
        this.ownerId = ownerId;
        this.properties = properties;
        this.ownerName = ownerName;
        this.contactInformation = contactInformation;
        this.email = email;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", properties=" + properties +
                ", ownerName='" + ownerName + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", email='" + email + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
