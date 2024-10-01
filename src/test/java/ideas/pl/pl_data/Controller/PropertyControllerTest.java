package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProperties() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        PropertyDTO property2 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1, property2);

        when(propertyService.getAllProperties()).thenReturn(properties);

        List<PropertyDTO> result = propertyController.getProperties();

        assertEquals(2, result.size());
        verify(propertyService, times(1)).getAllProperties();
    }

    @Test
    void getFeatuers() {
        List<String> features = Arrays.asList("Feature1", "Feature2");
        when(propertyService.featuers()).thenReturn(features);

        List<String> result = propertyController.getFeatuers();

        assertEquals(2, result.size());
        verify(propertyService, times(1)).featuers();
    }

    @Test
    void getPropertyById_Found() {
        PropertyDTO property = mock(PropertyDTO.class);
        when(propertyService.getPropertyById(1)).thenReturn(Optional.of(property));

        ResponseEntity<PropertyDTO> response = propertyController.getPropertyById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    @Test
    void getPropertyById_NotFound() {
        when(propertyService.getPropertyById(1)).thenReturn(Optional.empty());

        ResponseEntity<PropertyDTO> response = propertyController.getPropertyById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createProperty() {
        Property property = new Property();
        Property newProperty = new Property();
        when(propertyService.createProperty(property)).thenReturn(newProperty);

        ResponseEntity<Property> response = propertyController.createProperty(property);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newProperty, response.getBody());
        verify(propertyService, times(1)).createProperty(property);
    }

    @Test
    void updateProperty_Found() {
        Property updatedProperty = new Property();
        Property existingProperty = new Property();
        when(propertyService.updateProperty(anyInt(), any())).thenReturn(Optional.of(existingProperty));

        ResponseEntity<Property> response = propertyController.updateProperty(1, updatedProperty);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingProperty, response.getBody());
    }

    @Test
    void updateProperty_NotFound() {
        Property updatedProperty = new Property();
        when(propertyService.updateProperty(anyInt(), any())).thenReturn(Optional.empty());

        ResponseEntity<Property> response = propertyController.updateProperty(1, updatedProperty);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteProperty_Found() {
        when(propertyService.getPropertyById(1)).thenReturn(Optional.of(mock(PropertyDTO.class)));

        ResponseEntity<Void> response = propertyController.deleteProperty(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(propertyService, times(1)).deleteProperty(1);
    }

    @Test
    void deleteProperty_NotFound() {
        when(propertyService.getPropertyById(1)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = propertyController.deleteProperty(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void searchByFeatures() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);
        when(propertyService.findByFeatures(anyString())).thenReturn(properties);

        List<PropertyDTO> result = propertyController.searchByFeatures("feature");

        assertEquals(1, result.size());
        verify(propertyService, times(1)).findByFeatures("feature");
    }

    @Test
    void searchByName() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);
        when(propertyService.findByPropertyNameLike(anyString())).thenReturn(properties);

        List<PropertyDTO> result = propertyController.searchByName("name");

        assertEquals(1, result.size());
        verify(propertyService, times(1)).findByPropertyNameLike("name");
    }

    @Test
    void searchByAddress() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);
        when(propertyService.findByAddressLike(anyString())).thenReturn(properties);

        List<PropertyDTO> result = propertyController.searchByAddress("address");

        assertEquals(1, result.size());
        verify(propertyService, times(1)).findByAddressLike("address");
    }
}
