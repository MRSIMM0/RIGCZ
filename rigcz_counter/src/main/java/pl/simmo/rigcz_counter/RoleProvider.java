//package pl.simmo.rigcz_counter;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import pl.simmo.rigcz_counter.Entity.Role;
//import pl.simmo.rigcz_counter.Entity.RoleName;
//import pl.simmo.rigcz_counter.Repository.RoleRepository;
//
//@Component
//public class RoleProvider implements CommandLineRunner{
//
//    private RoleRepository roleRepository;
//
//    public RoleProvider(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if(roleRepository.findByName(RoleName.ROLE_USER).isPresent()==false){
//            roleRepository.save(new Role(RoleName.ROLE_USER));
//        }
//        if(roleRepository.findByName(RoleName.ROLE_ADMIN).isPresent()==false){
//            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
//        }
//        if(roleRepository.findByName(RoleName.ROLE_PM).isPresent()==false){
//            roleRepository.save(new Role(RoleName.ROLE_PM));
//        }
//
//
//    }
//}
