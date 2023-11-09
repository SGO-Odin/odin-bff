package com.odin.odinbff.model.purveyor;

import com.odin.odinbff.model.Email;
import com.odin.odinbff.model.Phone;
import com.odin.odinbff.model.address.Address;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class Purveyor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String tradingName;
    private final String companyName;
    private final Boolean isLaboratory;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final Address address;

    @OneToMany(mappedBy = "id.purveyor", cascade = CascadeType.ALL)
    private final Set<PurveyorPhone> phones = new HashSet<>();

    @OneToMany(mappedBy = "id.purveyor", cascade = CascadeType.ALL)
    private final Set<PurveyorEmail> emails = new HashSet<>();

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Purveyor() {
        this(null, null, null, null, null);
    }

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

    public void addEmail(final Email email, final Boolean isMain) {
        emails.add(new PurveyorEmail(this, email, isMain));
    }

    public void addPhone(final Phone phone, final Boolean isMain) {
        phones.add(new PurveyorPhone(this, phone, isMain));
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

    public Boolean isLaboratory() {
        return isLaboratory;
    }

    public Address getAddress() {
        return address;
    }

    public Set<PurveyorPhone> getPhones() {
        return Collections.unmodifiableSet(phones);
    }

    public Set<PurveyorEmail> getEmails() {
        return Collections.unmodifiableSet(emails);
    }
}
