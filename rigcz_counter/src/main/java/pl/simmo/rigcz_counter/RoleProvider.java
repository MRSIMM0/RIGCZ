package pl.simmo.rigcz_counter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.simmo.rigcz_counter.Entity.Role;
import pl.simmo.rigcz_counter.Entity.RoleName;
import pl.simmo.rigcz_counter.Entity.User;
import pl.simmo.rigcz_counter.Repository.RoleRepository;
import pl.simmo.rigcz_counter.Repository.UserRepo;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleProvider implements CommandLineRunner{

    private RoleRepository roleRepository;
    private UserRepo userRepo;

    public RoleProvider(RoleRepository roleRepository, UserRepo userRepo) {
        this.roleRepository = roleRepository;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName(RoleName.ROLE_USER).isPresent()==false){
            roleRepository.save(new Role(RoleName.ROLE_USER));
        }
        if(roleRepository.findByName(RoleName.ROLE_ADMIN).isPresent()==false){
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        }
        if(roleRepository.findByName(RoleName.ROLE_PM).isPresent()==false){
            roleRepository.save(new Role(RoleName.ROLE_PM));
        }

        String admin = "admin";
        if(userRepo.existsByUsername(admin).booleanValue()==false){
         Set<Role> roles = new HashSet<>();
         roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
         roles.add(roleRepository.findByName(RoleName.ROLE_PM).get());
         User user = new User(admin,admin,admin);
         user.setRoles(roles);
         userRepo.save(user);
        }
    }
}
