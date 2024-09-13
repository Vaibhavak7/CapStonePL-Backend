package ideas.pl.pl_data.DTO;

public interface BookmarkDTO {
    public int getBookmarkId();

    public BookingDTO.Property getProperty();
    public BookingDTO.AppUser getUser();

    interface AppUser{
        public int getUserId();
        public String getUserName();
    }

    interface Property{
        public int getPropertyId();
        public String getPropertyName();
    }
}
