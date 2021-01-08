package pl.simmo.rigcz_counter.Services;


import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.simmo.rigcz_counter.Entity.DbEntity;
import pl.simmo.rigcz_counter.Repository.EntityRepo;
import pl.simmo.rigcz_counter.message.request.DbEntityMod;

import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;


@Service
@EnableScheduling
public class SSEService {

    private EntityRepo entityRepo;

    public SSEService(EntityRepo entityRepo) {
        this.entityRepo = entityRepo;
    }

    public SseEmitter getMyData() throws InterruptedException {
        SseEmitter notifier = new SseEmitter(600L);
        try {
            List<DbEntity> entity = entityRepo.findAll();
            List<DbEntityMod> myData = entity.stream().map( e -> {
                DbEntityMod mod = new DbEntityMod();
                mod.setName(e.getName());
                mod.setCraetedBy(e.getCraetedBy());
                mod.setId(e.getId());
                mod.setRigczLevel(e.getRigczLevel());
                return mod;
            }).collect(Collectors.toList());

                notifier.send(SseEmitter.event().reconnectTime(500).data(myData));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return notifier;
    }





}

