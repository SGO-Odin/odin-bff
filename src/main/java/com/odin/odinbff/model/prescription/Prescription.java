package com.odin.odinbff.model.prescription;

import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import com.odin.odinbff.model.client.Client;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class Prescription extends HistoryLoggable<Prescription> implements HasLongId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final LocalDate expirationDate;
    private final Float additional;

    @ManyToOne(optional = false)
    private final Client client;

    @OneToMany(mappedBy = "id.prescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<VisionProblem> problems = new HashSet<>();

    @CreationTimestamp
    private final LocalDateTime createdOn;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Prescription() {
        this(null, null, null, null, null);
    }


    public Prescription(LocalDate expirationDate, Float additional, Client client) {
        this(null, expirationDate, additional, client, LocalDateTime.now());
    }

    private Prescription(final Long id, LocalDate expirationDate, Float additional, Client client, LocalDateTime createdOn) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.additional = additional;
        this.client = client;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Float getAdditional() {
        return additional;
    }

    public Client getClient() {
        return client;
    }

    public Set<VisionProblem> getProblems() {
        return Collections.unmodifiableSet(problems);
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @Transient
    public void addVisionProblem(VisionProblem.Types type,
                                 VisionProblem.PositionOfEyesType positionOfEyes,
                                 Float spherical,
                                 Float cylinder,
                                 Float axis,
                                 Float npd,
                                 Float height) {
        problems.add(new VisionProblem(this, type, positionOfEyes, spherical, cylinder, axis, npd, height));
    }
}