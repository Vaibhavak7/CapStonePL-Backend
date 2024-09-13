package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Exception.EmailAlreadyRegisteredException;
import ideas.pl.pl_data.Projection.AppUserProjection;
import ideas.pl.pl_data.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    // Get all users
    public List<AppUserProjection> getAllUsers() {
        return appUserRepository.findBy();
    }

    public AppUser register(AppUser user) {
        Optional<AppUser> userDb = appUserRepository.findByEmail(user.getEmail());
        if (!userDb.isEmpty()) {
            throw new EmailAlreadyRegisteredException("User with email already exists ");
        }
        return appUserRepository.save(user);
    }

    // Get user by ID
    public Optional<AppUserProjection> getUserById(int id) {
        return appUserRepository.findProjectedById(id);
    }

    // Create new user
    public AppUser createUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    // Update existing user
    public Optional<AppUser> updateUser(int id, AppUser updatedUser) {
        return appUserRepository.findById(id).map(user -> {
            user.setUserName(updatedUser.getUserName());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setContactInformation(updatedUser.getContactInformation());
            user.setTimestamp(updatedUser.getTimestamp());
            return appUserRepository.save(user);
        });
    }

    // Delete user by ID
    public void deleteUser(int id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
