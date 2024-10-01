package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends CrudRepository<Property, Integer> {

//    TODO : Find all Properties

    List<PropertyDTO> findBy();

    List<PropertyDTO> findByCityContainingIgnoreCase(String city);

    Optional<PropertyDTO> findByPropertyId(int propertyId);

    List<PropertyDTO> findByFeatures(@Param("feature") String feature);

    List<PropertyDTO> findByFeaturesContaining(String feature);

    List<PropertyDTO> findByPropertyNameContainingIgnoreCase(String propertyName);

    List<PropertyDTO> findByAddress1ContainingOrAddress2Containing(String address1, String address2);

    // can avoid this by dto @query
    @Query(value = "SELECT p.features AS features FROM Property p" )
    List<String> findByFeatuers();


}
