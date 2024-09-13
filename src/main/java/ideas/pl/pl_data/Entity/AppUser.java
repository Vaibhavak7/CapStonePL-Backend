package ideas.pl.pl_data.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "AppUser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Bookmark> bookmarks;

    private String userName;
    private String password;
    private String email;
    private String contactInformation;
    private LocalDateTime timestamp;
    private String role;
}

