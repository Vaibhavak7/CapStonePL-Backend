package ideas.pl.pl_data.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookmarkId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "property_Id")
    private Property property;

    public Bookmark() {

    }

    public Bookmark(int bookmarkId, AppUser user, Property property) {
        this.bookmarkId = bookmarkId;
        this.user = user;
        this.property = property;
    }



    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
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

    @Override
    public String toString() {
        return "\nBookmark [bookmarkId=" + bookmarkId + "]\n";
//        return "\nBookmark [bookmarkId=" + bookmarkId + ", user=" + user + ", property=" + property + "]\n";
    }
}
