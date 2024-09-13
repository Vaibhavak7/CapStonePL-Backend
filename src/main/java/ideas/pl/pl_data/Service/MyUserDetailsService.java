//package ideas.pl.pl_data.Service;
//
//
//import ideas.pl.pl_data.Repository.AppUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//    @Autowired
//    AppUserRepository userRepository;
//
////    @Override
////    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        // Load user from repository or other source
////        AppUser user = userRepository.findByUsername(username);
////        if (user == null) {
////            throw new UsernameNotFoundException("User not found");
////        }
////        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
////    }
//}