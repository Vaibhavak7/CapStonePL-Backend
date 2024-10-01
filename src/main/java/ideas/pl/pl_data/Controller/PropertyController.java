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
@CrossOrigin("*")
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    //TODO: Get all properties
    @GetMapping
    public List<PropertyDTO> getProperties() {
        return propertyService.getAllProperties();
    }

    //TODO: Get property by features
    @GetMapping({"/featuers"})
    public List<String> getFeatuers() {
        return propertyService.featuers();
    }

    //TODO: Get property by ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable int propertyId) {
        Optional<PropertyDTO> property = propertyService.getPropertyById(propertyId);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    //TODO:Get Property according to search
    @GetMapping("/search/{string1}")
    public List<PropertyDTO> getProperties(@PathVariable("string1") String string1) {
        // Process the strings as needed
        return propertyService.getAllPropertiesBySearch(string1);
    }

    //TODO: Get property by ID with Owner Details
    @GetMapping("/owner/{propertyId}")
    public ResponseEntity<PropertyDTO> getPropertyByIdWithOwner(@PathVariable int propertyId) {
        Optional<PropertyDTO> property = propertyService.getPropertyByIdWithOwner(propertyId);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // TODO:Create a new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property newProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    //TODO: Update an existing property
    @PutMapping("/{propertyId}")
    public ResponseEntity<Property> updateProperty(@PathVariable int propertyId, @RequestBody Property updatedProperty) {
        Optional<Property> property = propertyService.updateProperty(propertyId, updatedProperty);
        return property.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // TODO:Delete a property
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable int propertyId) {
        if (propertyService.getPropertyById(propertyId).isPresent()) {
            propertyService.deleteProperty(propertyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //TODO: Get Prop by Features
    @GetMapping("/search/features")
    public List<PropertyDTO> searchByFeatures(@RequestParam String feature) {
        return propertyService.findByFeatures(feature);
    }

    //TODO: Get property by name
    @GetMapping("/search/name")
    public List<PropertyDTO> searchByName(@RequestParam String name) {
        return propertyService.findByPropertyNameLike(name);
    }

    //TODO:Get property by address
    @GetMapping("/search/address")
    public List<PropertyDTO> searchByAddress(@RequestParam String address) {
        return propertyService.findByAddressLike(address);
    }
}
