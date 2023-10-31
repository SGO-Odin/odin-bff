package com.odin.odinbff.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Purveyor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String tradingName;
    private final String companyName;
    private final Boolean isLaboratory;

    @ManyToOne(optional = false)
    private final Address address;

    @OneToMany(mappedBy = "id.purveyor", fetch = FetchType.LAZY)
    private final Set<PurveyorPhone> phones = new HashSet<>();

    @OneToMany(mappedBy = "id.purveyor", fetch = FetchType.LAZY)
    private final Set<PurveyorEmail> emails = new HashSet<>();

    public Purveyor(String tradingName, String companyName, Boolean isLaboratory, Address address) {
        this(null, tradingName, companyName, isLaboratory, address);
    }

    private Purveyor(Long id,
                     String tradingName,
                     String companyName,
                     Boolean isLaboratory,
                     Address address) {
        this.id = id;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.isLaboratory = isLaboratory;
        this.address = address;
    }

    public void addEmail(final Email email) {
        emails.add(new PurveyorEmail(this, email));
    }

    public void addPhone(final Phone phone) {
        phones.add(new PurveyorPhone(this, phone));
    }

    public Long getId() {
        return id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Boolean getLaboratory() {
        return isLaboratory;
    }

    public Address getAddress() {
        return address;
    }

    public Set<PurveyorPhone> getPhones() {
        return phones;
    }

    public Set<PurveyorEmail> getEmails() {
        return emails;
    }
}
