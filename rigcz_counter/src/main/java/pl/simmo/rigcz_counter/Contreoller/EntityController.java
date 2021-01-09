package pl.simmo.rigcz_counter.Contreoller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.simmo.rigcz_counter.Entity.DbEntity;
import pl.simmo.rigcz_counter.Entity.User;
import pl.simmo.rigcz_counter.Repository.EntityRepo;
import pl.simmo.rigcz_counter.Repository.UserRepo;
import pl.simmo.rigcz_counter.Services.SSEService;
import pl.simmo.rigcz_counter.message.request.DbEntityMod;

import javax.activity.InvalidActivityException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class EntityController {

    private EntityRepo entityRepo;
    private SSEService service;
    private UserRepo userRepo;

    public EntityController(EntityRepo entityRepo, SSEService service, UserRepo userRepo) {
        this.entityRepo = entityRepo;
        this.service = service;
        this.userRepo = userRepo;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    @PostMapping("/new")
    public DbEntity add(@RequestBody DbEntityMod entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new Error("error");
        }
        DbEntity entity1 = new DbEntity(entity.getName(), 0, userRepo.findUserByUsername(entity.getCraetedBy()).orElseThrow(() -> new RuntimeException("Unknown Creator")), null, null);
        return entityRepo.save(entity1);
    }

    @GetMapping("/stream")
    public SseEmitter doNotify() throws InterruptedException {
        return service.getMyData();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    @PostMapping("/add")
    public ResponseEntity<String> update(@RequestBody DbEntityMod dbEntity) {
        DbEntity db = entityRepo.findById(dbEntity.getId()).orElseThrow(() -> new RuntimeException("Error"));

        Set<User> usersAdd = db.getUsersAdd();
        Set<User> userMinus = db.getUsersMinus();


        User user = userRepo.findUserByUsername(dbEntity.getUpdatingUser())
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        if (usersAdd.contains(user)) {
            return ResponseEntity.badRequest().body("User already used this option.");
        }
        if (userMinus.contains(user)) {
            userMinus.remove(user);
            db.setRigczLevel(db.getRigczLevel() + 1);
        }
        usersAdd.add(user);
        db.setRigczLevel(db.getRigczLevel() + 1);
        db.setUsersAdd(usersAdd);


        entityRepo.save(db);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    @PostMapping("/minus")
    public ResponseEntity<String> miuns(@RequestBody DbEntityMod dbEntity) {
        DbEntity db = entityRepo.findById(dbEntity.getId()).orElseThrow(() -> new RuntimeException("Error"));

        Set<User> usersAdd = db.getUsersAdd();
        Set<User> userMinus = db.getUsersMinus();


        User user = userRepo.findUserByUsername(dbEntity.getUpdatingUser())
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        if (userMinus.contains(user)) {
            return ResponseEntity.badRequest().body("User already used this option.");
        }

        if (usersAdd.contains(user)) {
            usersAdd.remove(user);
            db.setRigczLevel(db.getRigczLevel() - 1);
        }

        userMinus.add(user);
        db.setRigczLevel(db.getRigczLevel() - 1);
        db.setUsersMinus(userMinus);


        entityRepo.save(db);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUserEntities/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    ResponseEntity<?> getUserEntities(@PathVariable String username) {
        List<DbEntity> dbEntity = entityRepo.findByCreatedBy(userRepo.findUserByUsername(username).orElseThrow(() -> new RuntimeException("Unknown User.")));

        List<DbEntityMod> entity = dbEntity.stream().map(e -> {

            DbEntityMod mod = new DbEntityMod();

            mod.setId(e.getId());
            mod.setName(e.getName());
            mod.setRigczLevel(e.getRigczLevel());
            mod.setCraetedBy(e.getCreatedBy().getUsername());
            mod.setUserAdd(e.getUsersAdd().stream().map(user -> user.getUsername()).collect(Collectors.toSet()));
            mod.setUserMinus(e.getUsersAdd().stream().map(user -> user.getUsername()).collect(Collectors.toSet()));

            return mod;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(entity);
    }

    @PostMapping("/deleteEntity")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    public ResponseEntity<String> deleteEntity(@RequestBody DbEntityMod entity) {
        if (entityRepo.existsById(entity.getId())) {
            User requester = userRepo.findUserByUsername(entity.getUpdatingUser()).orElseThrow(() -> new RuntimeException("user doesn't exist"));
            requester.getRoles().forEach(role -> {
                switch (role.getName()) {
                    case ROLE_PM:
                    case ROLE_ADMIN:
                        entityRepo.deleteById(entity.getId());
                        break;
                    case ROLE_USER:
                        User creator = userRepo.findUserByUsername(entity.getCraetedBy()).orElseThrow(() -> new RuntimeException("Creator doesn't exist"));
                        if (creator != requester) {
                            throw new RuntimeException("You can't do that");
                        }
                        entityRepo.deleteById(entity.getId());
                        break;
                }
            });
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
