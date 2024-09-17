package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
    //    List<PropertyDTO> findBy();
    //    PropertyDTO findById(int id);
    //    List<Property> findBy()
//    //TODO : Find all Properties
//    @Query(value = "SELECT p.propertyId as propertyId,p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
//            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
//            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
//            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
//            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
//            "p.maxGuests AS maxGuests " +
//            "FROM Property p")
//    List<PropertyDTO> findAllPropertiesAsDTO();
////      Optional<PropertyDTO> findById(int id);
//
// Find all properties as DTO
    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests FROM Property p")
    List<PropertyDTO> findAllPropertiesAsDTO();
    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests FROM Property p")
    List<PropertyDTO> findBy();

    // Find property by ID as DTO
    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests FROM Property p WHERE p.propertyId = :propertyId")
    Optional<PropertyDTO> findByPropertyId(@Param("propertyId") int propertyId);

    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests,p.owner as owner FROM Property p WHERE p.propertyId = :propertyId")
    Optional<PropertyDTO> findByPropertyIdWithOwner(@Param("propertyId") int propertyId);

    // Find properties by features as DTO
    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests FROM Property p WHERE p.features LIKE %:feature%")
    List<PropertyDTO> findByFeatures(@Param("feature") String feature);

    // Find properties by name as DTO
    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests FROM Property p WHERE p.propertyName LIKE %:name%")
    List<PropertyDTO> findByPropertyNameLike(@Param("name") String name);

    // Find properties by address as DTO
    @Query(value = "SELECT p.propertyId AS propertyId, p.propertyName AS propertyName, p.address1 AS address1, p.address2 AS address2, " +
            "p.area AS area, p.city AS city, p.state AS state, p.zipcode AS zipcode, " +
            "p.typeOfProperty AS typeOfProperty, p.description AS description, p.rent AS rent, " +
            "p.securityDeposit AS securityDeposit, p.howOldProperty AS howOldProperty, " +
            "p.postedOn AS postedOn, p.features AS features, p.imageUrl AS imageUrl, " +
            "p.maxGuests AS maxGuests FROM Property p WHERE p.address1 LIKE %:address% OR p.address2 LIKE %:address%")
    List<PropertyDTO> findByAddressLike(@Param("address") String address);

}
