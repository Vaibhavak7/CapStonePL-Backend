package ideas.pl.pl_data.DTO;

public interface BookmarkDTO {
    public int getBookmarkId();

    public Property getProperty();
    public AppUser getUser();

    interface AppUser{
        public int getUserId();
        public String getUserName();
    }

    interface Property{
        public int getPropertyId();
        public String getPropertyName();
        public String getArea();
        public String getCity();
        public String getTypeOfProperty();
        public float getRent();
        public int getMaxGuests();
        public String getImageUrl();
    }
}
