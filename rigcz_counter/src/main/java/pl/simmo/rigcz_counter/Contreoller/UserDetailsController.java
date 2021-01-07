package pl.simmo.rigcz_counter.Contreoller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.simmo.rigcz_counter.Entity.User;
import pl.simmo.rigcz_counter.Repository.UserRepo;
import pl.simmo.rigcz_counter.message.response.UserDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class UserDetailsController {

    private UserRepo userRepo;

    public UserDetailsController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PM')")
    ResponseEntity<?> getUser(@PathVariable String username){
       if(userRepo.findUserByUsername(username).isPresent()){
           UserDetails u = new UserDetails();
           u.setName(userRepo.findUserByUsername(username).get().getName());
           return ResponseEntity.ok(u);

       }
       return ResponseEntity.notFound().build();
    }
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PM')")
    ResponseEntity<Set<UserDetails>> getUsers(){
        List<User> users = userRepo.findAll();

        Set<UserDetails> userDetailsSet  = users.stream().map(u ->{
            UserDetails userD = new UserDetails();
            userD.setName(u.getName());
            userD.setRole(u.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList()));
            userD.setUsername(u.getUsername());
            return userD;
        }).collect(Collectors.toSet());


        return ResponseEntity.ok(userDetailsSet);
    }

}
