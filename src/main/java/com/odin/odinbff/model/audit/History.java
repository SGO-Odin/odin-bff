package com.odin.odinbff.model.audit;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
public final class History {

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private History() {
        id = null;
        entity = null;
        eventType = null;
        oldData = null;
        result = null;
        registredOn = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;

    @NotNull
    @Column(nullable = false, length = 127)
    private final String entity;

    @Enumerated(value = EnumType.STRING)
    private final EventType eventType;

    @Convert(converter = LogDiffConverter.class)
    private final Map<String, Object> oldData;

    @Convert(converter = LogDiffConverter.class)
    @Column()
    private final Map<String, Object> result;

    @Column(nullable = false)
    private LocalDateTime registredOn = null;

    public enum EventType {
        CREATE, UPDATED, DELETED
    }


    private History(Class<?> entity,
                    final EventType eventType,
                    final Map<String, Object> oldData,
                    final Map<String, Object> result) {
        this.id = null;
        this.registredOn = null;
        this.entity = entity.getName();
        this.eventType = eventType;
        this.oldData = oldData;
        this.result = result;

        validate();
    }

    public static <Tp extends HistoryLoggable<Tp>> History create (final Tp newEntity)
            throws NoSuchFieldException, IllegalAccessException {

        return new History(newEntity.getClass(),
                EventType.CREATE,
                null,
                newEntity.logOfAllData());
    }

    public static <Tp extends HistoryLoggable<Tp>> History update (final Tp target,
                                                            final Tp updated)
            throws NoSuchFieldException, IllegalAccessException {

        return new History(target.getClass(), EventType.UPDATED, target.update(updated), target.logOfAllData());
    }

    public String getEntity() {
        return entity;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, Object> getHistory() {
        return oldData;
    }

    private void validate() {
        if (result == null || result.isEmpty()
                || (eventType == EventType.CREATE && oldData != null)
                || (eventType == EventType.UPDATED
                        && (oldData == null || oldData.isEmpty()))) {
            throw new IllegalStateException();
        }
    }

    @PrePersist
    private void prePersist(){
        this.registredOn = LocalDateTime.now();
    }
}
