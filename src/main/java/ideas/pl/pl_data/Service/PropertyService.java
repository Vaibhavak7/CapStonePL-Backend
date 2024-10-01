package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Repository.PropertyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findBy();
    }

    public List<PropertyDTO> getAllPropertiesBySearch(String city) {
        return propertyRepository.findByCityContainingIgnoreCase(city);
    }

    // Get property by ID as DTO
    public Optional<PropertyDTO> getPropertyById(int propertyId) {
        return propertyRepository.findByPropertyId(propertyId);
    }

    public Optional<PropertyDTO> getPropertyByIdWithOwner(int propertyId) {
        return propertyRepository.findByPropertyId(propertyId);
    }

    // Create a new property
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> updateProperty(int propertyId, Property updatedProperty) {
        return propertyRepository.findById(propertyId).map(property -> {
            property.setPropertyName(updatedProperty.getPropertyName());
            property.setAddress1(updatedProperty.getAddress1());
            property.setAddress2(updatedProperty.getAddress2());
            property.setCity(updatedProperty.getCity());
            property.setState(updatedProperty.getState());
            property.setZipcode(updatedProperty.getZipcode());
            property.setDescription(updatedProperty.getDescription());
            property.setRent(updatedProperty.getRent());
            property.setSecurityDeposit(updatedProperty.getSecurityDeposit());
            property.setHowOldProperty(updatedProperty.getHowOldProperty());
            return propertyRepository.save(property);
        });
    }

    public List<String> featuers()
    {
        return propertyRepository.findByFeatuers();
    }
    // Delete property by ID
    public void deleteProperty(int propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    // Additional search methods
    public List<PropertyDTO> findByFeatures(String feature) {
        return propertyRepository.findByFeaturesContaining(feature);
    }

    public List<PropertyDTO> findByPropertyNameLike(String name) {
        return propertyRepository.findByPropertyNameContainingIgnoreCase(name);
    }

    public List<PropertyDTO> findByAddressLike(String address) {
        return propertyRepository.findByAddress1ContainingOrAddress2Containing(address,address);
    }
}


