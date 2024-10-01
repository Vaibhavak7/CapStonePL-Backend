package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Property;
import ideas.pl.pl_data.Repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProperties() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        PropertyDTO property2 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1, property2);

        when(propertyRepository.findBy()).thenReturn(properties);

        List<PropertyDTO> result = propertyService.getAllProperties();

        assertEquals(2, result.size());
        verify(propertyRepository, times(1)).findBy();
    }

    @Test
    void getAllPropertiesBySearch() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);

        when(propertyRepository.findByCityContainingIgnoreCase("CityName")).thenReturn(properties);

        List<PropertyDTO> result = propertyService.getAllPropertiesBySearch("CityName");

        assertEquals(1, result.size());
        verify(propertyRepository, times(1)).findByCityContainingIgnoreCase("CityName");
    }

    @Test
    void getPropertyById() {
        PropertyDTO property = mock(PropertyDTO.class);
        when(propertyRepository.findByPropertyId(1)).thenReturn(Optional.of(property));

        Optional<PropertyDTO> result = propertyService.getPropertyById(1);

        assertTrue(result.isPresent());
        assertEquals(property, result.get());
        verify(propertyRepository, times(1)).findByPropertyId(1);
    }

    @Test
    void getPropertyById_NotFound() {
        when(propertyRepository.findByPropertyId(1)).thenReturn(Optional.empty());

        Optional<PropertyDTO> result = propertyService.getPropertyById(1);

        assertFalse(result.isPresent());
        verify(propertyRepository, times(1)).findByPropertyId(1);
    }

    @Test
    void createProperty() {
        Property property = new Property();
        when(propertyRepository.save(property)).thenReturn(property);

        Property result = propertyService.createProperty(property);

        assertEquals(property, result);
        verify(propertyRepository, times(1)).save(property);
    }

    @Test
    void updateProperty() {
        Property existingProperty = new Property();
        Property updatedProperty = new Property();
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.of(existingProperty));
        when(propertyRepository.save(existingProperty)).thenReturn(existingProperty);

        Optional<Property> result = propertyService.updateProperty(1, updatedProperty);

        assertTrue(result.isPresent());
        assertEquals(existingProperty, result.get());
        verify(propertyRepository, times(1)).findById(anyInt());
        verify(propertyRepository, times(1)).save(existingProperty);
    }

    @Test
    void updateProperty_NotFound() {
        Property updatedProperty = new Property();
        when(propertyRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Property> result = propertyService.updateProperty(1, updatedProperty);

        assertFalse(result.isPresent());
        verify(propertyRepository, times(1)).findById(anyInt());
    }

    @Test
    void featuers() {
        List<String> features = Arrays.asList("Feature1", "Feature2");
        when(propertyRepository.findByFeatuers()).thenReturn(features);

        List<String> result = propertyService.featuers();

        assertEquals(2, result.size());
        verify(propertyRepository, times(1)).findByFeatuers();
    }

    @Test
    void deleteProperty() {
        Property property = new Property();
        when(propertyRepository.findById(1)).thenReturn(Optional.of(property));

        propertyService.deleteProperty(1);

        verify(propertyRepository, times(1)).deleteById(1);
    }


    @Test
    void findByFeatures() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);
        when(propertyRepository.findByFeaturesContaining("feature")).thenReturn(properties);

        List<PropertyDTO> result = propertyService.findByFeatures("feature");

        assertEquals(1, result.size());
        verify(propertyRepository, times(1)).findByFeaturesContaining("feature");
    }

    @Test
    void findByPropertyNameLike() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);
        when(propertyRepository.findByPropertyNameContainingIgnoreCase("propertyName")).thenReturn(properties);

        List<PropertyDTO> result = propertyService.findByPropertyNameLike("propertyName");

        assertEquals(1, result.size());
        verify(propertyRepository, times(1)).findByPropertyNameContainingIgnoreCase("propertyName");
    }


    @Test
    void findByAddressLike() {
        PropertyDTO property1 = mock(PropertyDTO.class);
        List<PropertyDTO> properties = Arrays.asList(property1);

        when(propertyRepository.findByAddress1ContainingOrAddress2Containing("address1", "address1"))
                .thenReturn(properties);

        List<PropertyDTO> result = propertyService.findByAddressLike("address1");

        assertEquals(1, result.size());
        verify(propertyRepository, times(1)).findByAddress1ContainingOrAddress2Containing("address1", "address1");
    }

}
