package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.DTO.UserLoginDTO;
import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Projection.AppUserProjection;
import ideas.pl.pl_data.Repository.AppUserRepository;
import ideas.pl.pl_data.Service.UserService;
import ideas.pl.pl_data.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    private UserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    //TODO:Get all users
    @GetMapping
    public List<AppUserProjection> getAllUsers() {
        return appUserService.getAllUsers();
    }

    //TODO:Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AppUserProjection> getUserById(@PathVariable int id) {
        Optional<AppUserProjection> user = appUserService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //TODO: Register User
    @PostMapping("/register")
    public AppUser registerUser(@RequestBody AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return appUserService.register(user);

    }

    //TODO: Login User
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        UserDetails userDetails = appUserService.loadUserByUsername(loginRequest.getEmail());

        String jwtToken = jwtUtil.generateToken(userDetails);

        Optional<AppUser> userSaved = userRepository.findByEmail(loginRequest.getEmail());
        record UserResponse (int userId,String email,String password,String role,String token,String userName){}

        UserResponse u = new UserResponse(userSaved.get().getUserId(),userSaved.get().getEmail(),userSaved.get().getPassword(),userSaved.get().getRole(),jwtToken,userSaved.get().getUserName());
        return ResponseEntity.ok().body(u);
    }

    //TODO:Create new user
    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        AppUser newUser = appUserService.createUser(appUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //TODO:Update user
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable int id, @RequestBody AppUser updatedUser) {
        Optional<AppUser> user = appUserService.updateUser(id, updatedUser);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //TODO:Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        appUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}