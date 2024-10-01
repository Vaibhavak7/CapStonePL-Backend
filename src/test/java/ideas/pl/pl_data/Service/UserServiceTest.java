package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Exception.EmailAlreadyRegisteredException;
import ideas.pl.pl_data.Projection.AppUserProjection;
import ideas.pl.pl_data.Repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<AppUserProjection> users = new ArrayList<>();
        // Add some mock data if necessary
        when(userRepository.findBy()).thenReturn(users);

        List<AppUserProjection> result = userService.getAllUsers();

        assertEquals(users, result);
        verify(userRepository, times(1)).findBy();
    }

    @Test
    void register() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser result = userService.register(user);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void register_UserAlreadyExists() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyRegisteredException.class, () -> userService.register(user));
        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, never()).save(any(AppUser.class));
    }

    @Test
    void getUserById() {
        AppUser user = new AppUser();
        user.setUserId(1);
        user.setUserName("user1");
        user.setEmail("user1@example.com");

        AppUserProjection userProjection = new AppUserProjection() {
            @Override
            public int getUserId() {
                return user.getUserId();
            }

            @Override
            public String getUserName() {
                return user.getUserName();
            }

            @Override
            public String getEmail() {
                return user.getEmail();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public List<FeedbackProjection> getFeedbacks() {
                return List.of();
            }

            @Override
            public List<BookingProjection> getBookings() {
                return List.of();
            }

            @Override
            public List<BookmarkProjection> getBookmarks() {
                return List.of();
            }
        };


        when(userRepository.findByUserId(1)).thenReturn(Optional.of(userProjection));


        Optional<AppUserProjection> result = userService.getUserById(1);


        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().getEmail());
        assertEquals(user.getUserName(), result.get().getUserName());
        verify(userRepository, times(1)).findByUserId(1);
    }

    @Test
    void createUser() {
        AppUser user = new AppUser();
        user.setEmail("newuser@example.com");

        when(userRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
        AppUser existingUser = new AppUser();
        existingUser.setUserId(1);
        existingUser.setEmail("user1@example.com");
        existingUser.setPassword("oldPassword");

        AppUser updatedUser = new AppUser();
        updatedUser.setEmail("updatedUser@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(AppUser.class))).thenReturn(existingUser);

        Optional<AppUser> result = userService.updateUser(1, updatedUser);

        assertTrue(result.isPresent());
        assertEquals("updatedUser@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void deleteUser() {
        int userId = 1;

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
