package pl.simmo.rigcz_counter.Contreoller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.simmo.rigcz_counter.Entity.DbEntity;
import pl.simmo.rigcz_counter.Entity.User;
import pl.simmo.rigcz_counter.Repository.EntityRepo;
import pl.simmo.rigcz_counter.Repository.UserRepo;
import pl.simmo.rigcz_counter.Services.SSEService;
import pl.simmo.rigcz_counter.message.request.DbEntityMod;

import javax.activity.InvalidActivityException;
import java.util.Set;

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
        DbEntity entity1 = new DbEntity(entity.getName(), 0, entity.getCraetedBy(), null, null);
        return entityRepo.save(entity1);
    }

    @GetMapping("/stream")
    public SseEmitter doNotify() throws InterruptedException {
        return service.getMyData();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody DbEntityMod dbEntity) {
        DbEntity db = entityRepo.findById(dbEntity.getId()).orElseThrow(() -> new RuntimeException("Error"));

        Set<User> usersAdd = db.getUsersAdd();
        Set<User> userMinus = db.getUsersMinus();

        if (dbEntity.getUserAdd() != null) {
            User user = userRepo.findUserByUsername(dbEntity.getUserAdd())
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
        }

        if (dbEntity.getUserMinus() != null) {
            User user = userRepo.findUserByUsername(dbEntity.getUserMinus())
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
        }

        entityRepo.save(db);

        return ResponseEntity.ok().build();
    }
}
