package pl.simmo.rigcz_counter.Services;


import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.simmo.rigcz_counter.Entity.DbEntity;
import pl.simmo.rigcz_counter.Repository.EntityRepo;

import java.io.IOException;

import java.util.*;


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
            List<DbEntity> myData = entityRepo.findAll();

                notifier.send(SseEmitter.event().reconnectTime(500).data(myData));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return notifier;
    }





}

