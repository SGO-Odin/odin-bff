package com.odin.odinbff.service;

import com.odin.odinbff.model.Activatable;
import com.odin.odinbff.model.audit.History;
import com.odin.odinbff.model.audit.HistoryLoggable;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ActivableService {

    private final EntityManager entityManager;

    public ActivableService(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public <T extends HistoryLoggable<T> & Activatable> void activate(final T updatedEntity) throws IllegalAccessException {
        if(!updatedEntity.isActive()) {
            updatedEntity.activate();
            var his = new History(
                    updatedEntity.getClass(),
                    History.EventType.UPDATED,
                    Map.of("isActivate", false),
                    updatedEntity.logOfAllData());
            entityManager.persist(his);
            entityManager.persist(updatedEntity);
        }
    }

    @Transactional
    public <T extends HistoryLoggable<T> & Activatable> void inactivate(final T updatedEntity) throws IllegalAccessException {
        if(updatedEntity.isActive()) {
            updatedEntity.inactivate();
            var his = new History(
                    updatedEntity.getClass(),
                    History.EventType.UPDATED,
                    Map.of("isActivate", true),
                    updatedEntity.logOfAllData());
            entityManager.persist(his);
            entityManager.persist(updatedEntity);
        }
    }

}
