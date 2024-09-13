package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Service.PropertyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // Get all properties
    @GetMapping
    public List<PropertyDTO> getProperties() {
        return propertyService.getAllProperties();
    }

    // Get property by ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable int propertyId) {
        Optional<PropertyDTO> property = propertyService.getPropertyById(propertyId);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    // Get property by ID with Owner Details
    @GetMapping("/owner/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyByIdWithOwner(@PathVariable int propertyId) {
        Optional<PropertyDTO> property = propertyService.getPropertyByIdWithOwner(propertyId);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Create a new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property newProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    // Update an existing property
    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable int propertyId, @RequestBody Property updatedProperty) {
        Optional<Property> property = propertyService.updateProperty(propertyId, updatedProperty);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Delete a property
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable int propertyId) {
        if (propertyService.getPropertyById(propertyId).isPresent()) {
            propertyService.deleteProperty(propertyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Additional search endpoints
    @GetMapping("/search/features")
    public List<PropertyDTO> searchByFeatures(@RequestParam String feature) {
        return propertyService.findByFeatures(feature);
    }

    @GetMapping("/search/name")
    public List<PropertyDTO> searchByName(@RequestParam String name) {
        return propertyService.findByPropertyNameLike(name);
    }

    @GetMapping("/search/address")
    public List<PropertyDTO> searchByAddress(@RequestParam String address) {
        return propertyService.findByAddressLike(address);
    }
}
