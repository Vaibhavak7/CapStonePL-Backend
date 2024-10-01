package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.UserLoginDTO;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Projection.AppUserProjection;
import ideas.pl.pl_data.Repository.AppUserRepository;
import ideas.pl.pl_data.Service.UserService;
import ideas.pl.pl_data.Util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<AppUserProjection> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);

        List<AppUserProjection> result = userController.getAllUsers();

        assertEquals(users, result);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById() {
        AppUserProjection userProjection = mock(AppUserProjection.class);
        when(userService.getUserById(1)).thenReturn(Optional.of(userProjection));

        ResponseEntity<AppUserProjection> response = userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userProjection, response.getBody());
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void getUserById_NotFound() {
        when(userService.getUserById(1)).thenReturn(Optional.empty());

        ResponseEntity<AppUserProjection> response = userController.getUserById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void registerUser() {
        AppUser user = new AppUser();
        user.setEmail("test@example.com");
        user.setPassword("password");
        when(userService.register(any(AppUser.class))).thenReturn(user);

        AppUser result = userController.registerUser(user);

        assertEquals(user, result);
        verify(userService, times(1)).register(any(AppUser.class));
    }

    @Test
    void createUser() {
        AppUser user = new AppUser();
        when(userService.createUser(any(AppUser.class))).thenReturn(user);

        ResponseEntity<AppUser> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).createUser(any(AppUser.class));
    }

    @Test
    void updateUser() {
        AppUser updatedUser = new AppUser();
        when(userService.updateUser(1, updatedUser)).thenReturn(Optional.of(updatedUser));

        ResponseEntity<AppUser> response = userController.updateUser(1, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
        verify(userService, times(1)).updateUser(1, updatedUser);
    }

    @Test
    void updateUser_NotFound() {
        AppUser updatedUser = new AppUser();
        when(userService.updateUser(1, updatedUser)).thenReturn(Optional.empty());

        ResponseEntity<AppUser> response = userController.updateUser(1, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).updateUser(1, updatedUser);
    }

    @Test
    void deleteUser() {
        doNothing().when(userService).deleteUser(1);

        ResponseEntity<Void> response = userController.deleteUser(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1);
    }
}
