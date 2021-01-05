package pl.simmo.rigcz_counter.Contreoller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.simmo.rigcz_counter.Repository.UserRepo;
import pl.simmo.rigcz_counter.message.response.UserDetails;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class UserDetailsController {

    private UserRepo userRepo;

    public UserDetailsController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PM')")
    ResponseEntity<?> getUser(@RequestBody String username){
       if(userRepo.findUserByUsername(username).isPresent()){
           UserDetails u = new UserDetails();
           u.setName(userRepo.findUserByUsername(username).get().getName());
           u.setRole(userRepo.findUserByUsername(username).get().getRoles().stream().map(i -> i.toString()).collect(Collectors.toList()));
           return ResponseEntity.ok(u);

       }
       return ResponseEntity.notFound().build();
    }

}
