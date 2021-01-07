package pl.simmo.rigcz_counter.Contreoller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.simmo.rigcz_counter.Entity.DbEntity;
import pl.simmo.rigcz_counter.Repository.EntityRepo;
import pl.simmo.rigcz_counter.Services.SSEService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EntityController {

    private EntityRepo entityRepo;
    private SSEService service;


    public EntityController(EntityRepo entityRepo, SSEService SSEService) {
        this.entityRepo = entityRepo;
        this.service = SSEService;
    }




    @PreAuthorize("hasRole('ADMIN') or hasRole('PM') or hasRole('USER')")
    @PostMapping("/new")
    public DbEntity add(@RequestBody DbEntity entity, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw  new Error("error");
        }
       return entityRepo.save(entity);
    }

    @GetMapping("/stream")
    public SseEmitter doNotify() throws InterruptedException {
    return service.getMyData();
    }
    @PostMapping("/update")
    public HttpStatus update(@RequestBody DbEntity dbEntity){
       entityRepo.save(dbEntity);
        return HttpStatus.ACCEPTED;
    }
}
